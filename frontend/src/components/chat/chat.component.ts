import { HttpClient } from "@angular/common/http";
import { AfterViewChecked, Component, ElementRef, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import * as SockJS from "sockjs-client";
import * as Stomp from "stompjs";
import { FormControl } from "@angular/forms";
import { Observable, of } from "rxjs";
import { ChatService } from "./chat.service";
import { IUser, message } from "src/app/interfaces";

@Component({
  selector: "app-chat",
  templateUrl: "./chat.component.html",
  styleUrls: ["./chat.component.scss"],
})
export class ChatComponent implements OnInit, AfterViewChecked {
  url = "http://localhost:8080";
  otherUser?: IUser;
  thisUser: IUser;
  channelName?: string;
  socket?: WebSocket;
  stompClient?: Stomp.Client;
  newMessage = new FormControl("");
  messages?: Observable<Array<message>>;
  isFamilyChat: boolean = false;

  familyId: string;

  constructor(
    private route: ActivatedRoute,
    private chatService: ChatService,
    private http: HttpClient,
    private el: ElementRef
  ) {}

  ngAfterViewChecked(): void {
    this.scrollDown();
  }

  ngOnInit() {
    if (this.route.snapshot.url[1].path.includes("familyId")) {
      this.isFamilyChat = true;
    }

    if (!this.isFamilyChat) {
      this.chatService
        .getUserByEmail(sessionStorage.getItem("email"))
        .subscribe((result) => {
          this.thisUser = result;

          this.chatService
            .getUserByEmail(this.route.snapshot.paramMap.get("user")!)
            .subscribe((data) => {
              this.otherUser = data;
              this.connectToChat();
              // this.el.nativeElement.querySelector(".msger-chat").scrollIntoView();
            });
        });
    } else {
      this.familyId = this.route.snapshot.url[1].path.split("&")[1];

      this.chatService
        .getUserByEmail(sessionStorage.getItem("email"))
        .subscribe((result) => {
          this.thisUser = result;
          this.connectToChat();
        });
    }
  }

  scrollDown() {
    var container = this.el.nativeElement.querySelector(".msger-chat");
    container.scrollTop = container.scrollHeight;
  }

  connectToChat() {
    if (!this.isFamilyChat) {
      const id1 = this.thisUser.id;
      const nick1 = this.thisUser.email;
      const id2 = this.otherUser.id;
      const nick2 = this.otherUser.email;

      if (id1 > id2) {
        this.channelName = nick1 + "&" + nick2;
      } else {
        this.channelName = nick2 + "&" + nick1;
      }
      this.loadChat();
      this.socket = new SockJS(this.url + "/chat");
      this.stompClient = Stomp.over(this.socket);

      this.stompClient.connect({}, (frame) => {
        this.stompClient!.subscribe(
          "/topic/messages/" + this.channelName,
          () => {
            this.loadChat();
          }
        );
      });
    } else {
      this.channelName = "familyId&" + this.familyId;
      this.loadChat();
      this.socket = new SockJS(this.url + "/chat");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect({}, (frame) => {
        this.stompClient!.subscribe(
          "/topic/messages/" + this.channelName,
          () => {
            this.loadChat();
          }
        );
      });
    }
  }

  sendMsg() {
    if (this.newMessage.value !== "") {
      this.stompClient!.send(
        "/app/chat/" + this.channelName,
        {},
        JSON.stringify({
          ownerId: this.thisUser,
          content: this.newMessage.value,
        })
      );
      this.newMessage.setValue("");
    }
  }

  loadChat() {
    this.messages = this.http.post<Array<message>>(
      this.url + "/getMessages",
      this.channelName
    );
    this.messages.subscribe((data) => {
      let mgs: Array<message> = data;
      mgs.sort((a, b) => (a.id > b.id ? 1 : -1));
      this.messages = of(mgs);
    });
  }
}
