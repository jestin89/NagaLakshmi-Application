import { LoginResponse } from "./loginResponse";

export class ApplicationRequest {
    id: number;
    studentName: string;
    registerNo: string;
    education: string;
    instituteName: string;
    course: string;
    department: string;
    mobileNo: number;
    emailId: string;
    userId: number;
    bonafide: AttachedResponse;
}
export class AttachedResponse {
    id: number;
    fileName: string;
    content: any;
    fileType: string;
    register: LoginResponse;
}