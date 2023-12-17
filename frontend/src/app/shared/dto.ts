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
  JACHTOWY_STERNIK_MORSKI,
  MOTOROWODNY_STERNIK_MORSKI,
  ZEGLARZ_JACHTOWY,
  WARSZTATY_NAWIGACYJNE,
  REJSY_STAZOWE
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
  category: string,
  firstAnswer: string,
  secondAnswer: string,
  thirdAnswer: string,
  correctAnswer: string
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

export interface CourseFilterDTO {
  id: number,
  dateFrom: Date,
  dateTo: Date,
  courseStatus: CourseStatus,
  maxParticipantsNumber: number,
  city: CourseCity,
  courseType: CourseType,
  registeredParticipants: number
}

export interface ErrorDTO {
  fieldName: string,
  message: string
}

// Exist only on frontend, created for showing validation error below input fields
export interface GroupedErrorDTO {
  fieldName: string,
  messages: string[]
}
