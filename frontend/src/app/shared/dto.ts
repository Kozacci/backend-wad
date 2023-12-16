/** ---------------------- ENUMS ---------------------- **/
export enum Role {
  CLIENT,
  ADMIN
}

export enum CourseStatus {
  NIEROZPOCZETY,
  ROZPOCZETY,
  ZAKONCZONY
}

export enum CourseCity {
  SOPOT,
  OLECKO
}

export enum CourseType {
  STERNIK_MOTOROWODNY,
  MOTOROWODNY_STERNIK_MORSKI,
  HOLOWANIE_NARCIARZA_I_OBIEKTOW_NAWODNYCH,
  ZEGLARZ_JACHTOWY,
  JACHTOWY_STERNIK_MORSKI
}

export enum EventCity {
  SOPOT,
  OLECKO,
  GDANSK
}

export enum EventType {
  REJS_WIDOKOWY,
  PANIENSKI,
  KAWALERSKI,
  WYNAJEM_SKUTERA,
  EVENT_DLA_FIRMY
}

export enum Category {
  PRZEPISY,
  PODSTAWY_LOCJI,
  WIADOMOSCI_Z_METEOROLOGII,
  PODSTAWY_BUDOWY_JACHTOW,
  SILNIKI_I_UKLADY_NAPEDOWE,
  WIADOMOSCI_Z_RATOWNICTWA_WODNEGO,
  POMOCE_NAWIGACYJNE,
  OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM,
  PODSTAWOWE_PRZEPISY_PRAWA,
  TEORIA_ZEGLOWANIA
}

export enum CorrectAnswer {
  FIRST_ANSWER,
  SECOND_ANSWER,
  THIRD_ANSWER
}

/** ---------------------- INTERFACES ---------------------- **/

export interface ParticipantCourseEntityDTO {
  courseId: number,
  courseType: string,
  courseDateFrom: Date,
  courseDateTo: Date,
  accessDate: Date,
  participantId: number,
  participantEmail: string,
  participantLastName: string,
  isPassed: boolean,
  isPaid: boolean,
  onlinePayment: boolean
}

export interface ParticipantEntityDTO {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  phoneNumber: string,
  courses: ParticipantCourseEntityDTO[],
  role: Role
}

export interface ParticipantLoginDTO {
  email: string,
  password: string
}

export interface ParticipantRegisterDTO {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  phoneNumber: string
}

export interface QuestionFilterDTO {
  id: number,
  content: string,
  category: Category,
  firstAnswer: string,
  secondAnswer: string,
  thirdAnswer: string
}

export interface QuestionCreateUpdateDTO {
  content: string,
  category: Category,
  firstAnswer: string,
  secondAnswer: string,
  thirdAnswer: string,
  correctAnswer: CorrectAnswer
}

export interface QuestionEntityDTO {
  questionId: number,
  content: string,
  category: string,
  firstAnswer: string,
  secondAnswer: string,
  thirdAnswer: string,
  correctAnswer: string,
  explanation?: string,
  image?: string
}
