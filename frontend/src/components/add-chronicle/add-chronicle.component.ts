import { Component, OnInit } from "@angular/core";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { IChronicle } from "src/app/interfaces";
import { UtilFunctions } from "src/assets/utils/functions";
import { FamilyService } from "../family/family.service";
import { AddChronicleService } from "./add-chronicle.service";

declare var webkitSpeechRecognition: any;

@Component({
  selector: "app-add-chronicle",
  templateUrl: "./add-chronicle.component.html",
  styleUrls: ["./add-chronicle.component.scss"],
})
export class AddChronicleComponent implements OnInit {
  signsArray: string[][] = [
    ["przecinek", ","],
    ["kropka", "."],
    ["średnik", ";"],
    ["dwukropek", ":"],
    ["myślnik", "-"],
    ["pauza", "-"],
    ["wielokropek", "..."],
    ["pytajnik", "?"],
    ["znak zapytania", "?"],
    ["wykrzyknik", "!"],
    ["otwarty nawias", "("],
    ["zamknięty nawias", ")"],
    ["cudzysłów", '"'],
  ];

  speaking: boolean = false;
  recognition: any;
  isBrowserSupportSpeechRecognition = false;
  newChronicleForm: FormGroup;
  newChronicle: IChronicle = {
    id: null,
    content: null,
    date: null,
    name: null,
    authorId: null,
    familyId: null,
  };
  familyId: number;

  constructor(
    private addChronicleService: AddChronicleService,
    private familyService: FamilyService,
    private route: ActivatedRoute,
    private utilFunctions: UtilFunctions,
    private router: Router
  ) {}

  ngOnInit() {
    this.familyId = this.route.snapshot.params["id"];
    this.newChronicleForm = new FormGroup({
      name: new FormControl("", [
        Validators.required,
        Validators.maxLength(50),
      ]),
      content: new FormControl("", [Validators.required]),
      date: new FormControl("", [Validators.required]),
    });

    if ("SpeechRecognition" in window || "webkitSpeechRecognition" in window) {
      this.isBrowserSupportSpeechRecognition = true;
      this.recognition = new webkitSpeechRecognition();
      this.recognition.continuous = true;
      this.recognition.lang = 'pl-PL';
      this.recognition.onresult = () => this.startSpeaking(event);
    } else {
      console.log("Your browser doesn't support speech recognition");
    }
  }

  onSubmit(formValues) {
    if (this.newChronicleForm.invalid) {
      if (this.newChronicleForm.controls["name"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid name field value (name is required and allows a max length of 50 characters)",
          "Close",
          4000
        );
      } else if (this.newChronicleForm.controls["content"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid content field value (content is required)",
          "Close",
          4000
        );
      } else if (this.newChronicleForm.controls["date"].invalid) {
        this.utilFunctions.displayErrorSnackbar(
          "Invalid date field value (date is required)",
          "Close",
          4000
        );
      } else {
        this.utilFunctions.displayErrorSnackbar(
          "incorrect form values",
          "Close",
          4000
        );
      }
    } else {
      this.newChronicle.name = formValues.name;
      this.newChronicle.content = formValues.content;
      this.newChronicle.date = formValues.date;
      this.familyService
        .getCurrentUserIdByEmail(sessionStorage.getItem("email"))
        .subscribe(
          (result) => {
            this.newChronicle.authorId = result;
            this.newChronicle.familyId = this.familyId;
            this.addChronicleService
              .createNewChronicle(this.newChronicle)
              .subscribe(
                () => {
                  this.router.navigate([
                    "/my-families/id/" + this.familyId + "/chronicles",
                  ]);
                  this.utilFunctions.displaySuccessSnackbar(
                    "Successfully created chronicle",
                    "Close",
                    4000
                  );
                },
                (error) => {
                  this.utilFunctions.displayErrorSnackbar(
                    error.error.message,
                    "Close",
                    4000
                  );
                }
              );
          },
          (error) => {
            this.utilFunctions.displayErrorSnackbar(error.error.message, "Close", 4000);
          }
        );
    }
  }

  addSpeech() {
    if (this.recognition == null) {
      this.recognition = new webkitSpeechRecognition();
    }
    this.speaking ? this.recognition.stop() : this.recognition.start();
    this.speaking = !this.speaking;
    document.querySelector("#speechText").innerHTML = this.speaking
      ? "Click to stop:"
      : "Click to speak:";
  }

  startSpeaking(event) {
    const current = event.resultIndex;
    console.log(event.results[0][0].transcript);
    for (let i = current; i < event.results.length; ++i) {
      if (event.results[i].isFinal) {
        let word: string = event.results[i][0].transcript;
        word = this.replaceSigns(word);
        this.newChronicleForm.controls["content"].setValue(
          this.newChronicleForm.controls["content"].value + " " + word
        );
      }
    }
  }

  replaceSigns(sentence: string) {
    for (let sign of this.signsArray) {
      sentence = sentence.replace(new RegExp("\\s*" + sign[0], "gm"), sign[1]);
    }
    return sentence;
  }
}
