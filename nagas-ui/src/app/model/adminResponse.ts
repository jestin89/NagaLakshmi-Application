export class Admin{
    id:number;
    userName:string;   
    emailId: string;
    mobileNo: number;
    role: string;
}
export class AdminResponse{
    student: Admin[];
    subscriber:Admin[];
}