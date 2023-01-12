export interface IRegister {
  email: string;
  name: string;
  surname: string;
  password: string;
}

export interface INewsletter {
  id: number;
  email: string;
}

export interface IFamily {
  id: number;
  description: string;
  image: string;
  name: string;
  ownerId: number;
  surnames: string[];
  ownerData: string;
}

export interface IInvitation {
  id: number;
  type: string;
  familyId: number;
  userId: number;
}


export interface IUserDetails {
  id: number,
  image: string,
  name: string, 
  phone: string,
  surname: string
}

export interface message {
  id: number;
  content: string;
  date: Date;
  discussionId: number;
  ownerId: number;
}

export interface IChronicle {
  id: number;
  content: string;
  date: Date;
  name: string;
  authorId: number;
  familyId: number;
}

export interface IUser {
  id: number;
  email: string;
  userDetailsId: IUserDetails;
}