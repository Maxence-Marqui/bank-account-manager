export interface User {
    id: number;
    firstName: string;
    lastName: string;
}

export interface Account {
    id: number;
    accountName: string;
    accountNumber: string;
    balance: number;
    mainUser: User;
    users: User[];
}