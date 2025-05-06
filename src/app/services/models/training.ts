export interface User {
  id: number;
  username: string;
  email: string;
}

export interface Training {
  id?: number;
  title: string;
  description: string;
  category: string;
  location: string;
  deadline: string;
  type: string;
  postedBy: User;
}
