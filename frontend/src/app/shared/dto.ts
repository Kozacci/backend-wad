/** ---------------------- ENUMS ---------------------- **/
export enum Role {
  CLIENT,
  ADMIN
}

export enum CourseStatus {
  NIEROZPOCZETY = "NIEROZPOCZETY",
  ROZPOCZETY = "ROZPOCZETY",
  ZAKONCZONY = "ZAKONCZONY"
}

export enum CourseCity {
  SOPOT = "SOPOT",
  OLECKO = "OLECKO"
}

export enum CourseType {
  STERNIK_MOTOROWODNY = "STERNIK_MOTOROWODNY",
  JACHTOWY_STERNIK_MORSKI = "JACHTOWY_STERNIK_MORSKI",
  MOTOROWODNY_STERNIK_MORSKI = "MOTOROWODNY_STERNIK_MORSKI",
  ZEGLARZ_JACHTOWY = "ZEGLARZ_JACHTOWY",
  WARSZTATY_NAWIGACYJNE = "WARSZTATY_NAWIGACYJNE",
  REJSY_STAZOWE = "REJSY_STAZOWE"
}

export enum EventCity {
  SOPOT = "SOPOT",
  OLECKO = "OLECKO",
  GDANSK = "GDANSK"
}

export enum EventType {
  REJS_WIDOKOWY = "REJS_WIDOKOWY",
  PANIENSKI = "PANIENSKI",
  KAWALERSKI = "KAWALERSKI",
  WYNAJEM_SKUTERA = "WYNAJEM_SKUTERA",
  EVENT_DLA_FIRMY = "EVENT_DLA_FIRMY"
}

export enum Category {
  PRZEPISY = "PRZEPISY",
  PODSTAWY_LOCJI = "PODSTAWY_LOCJI",
  WIADOMOSCI_Z_METEOROLOGII = "WIADOMOSCI_Z_METEOROLOGII",
  PODSTAWY_BUDOWY_JACHTOW = "PODSTAWY_BUDOWY_JACHTOW",
  SILNIKI_I_UKLADY_NAPEDOWE = "SILNIKI_I_UKLADY_NAPEDOWE",
  WIADOMOSCI_Z_RATOWNICTWA_WODNEGO = "WIADOMOSCI_Z_RATOWNICTWA_WODNEGO",
  POMOCE_NAWIGACYJNE = "POMOCE_NAWIGACYJNE",
  OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM = "OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM",
  PODSTAWOWE_PRZEPISY_PRAWA = "PODSTAWOWE_PRZEPISY_PRAWA",
  TEORIA_ZEGLOWANIA = "TEORIA_ZEGLOWANIA"
}

export function mapToCategory(categoryNameAsString: string): Category {
  console.log("categoryNameAsString:", categoryNameAsString);
  switch (categoryNameAsString) {
    case "Przepisy":
    case "PRZEPISY":
      return Category.PRZEPISY;
    case "Podstawy locji":
    case "PODSTAWY_LOCJI":
      return Category.PODSTAWY_LOCJI;
    case "Wiadomości z zakresu meteorologii":
    case "WIADOMOSCI_Z_METEOROLOGII":
      return Category.WIADOMOSCI_Z_METEOROLOGII;
    case "Podstawy budowy jachtów motorowodnych":
    case "PODSTAWY_BUDOWY_JACHTOW":
      return Category.PODSTAWY_BUDOWY_JACHTOW;
    case "Silniki i układy napędowe":
    case "SILNIKI_I_UKLADY_NAPEDOWE":
      return Category.SILNIKI_I_UKLADY_NAPEDOWE;
    case "Wiadomości z zakresu ratownictwa wodnego":
    case "WIADOMOSCI_Z_RATOWNICTWA_WODNEGO":
      return Category.WIADOMOSCI_Z_RATOWNICTWA_WODNEGO;
    case "Pomoce nawigacyjne":
    case "POMOCE_NAWIGACYJNE":
      return Category.POMOCE_NAWIGACYJNE;
    case "Ochrona wód przed zanieczyszczeniem":
    case "OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM":
      return Category.OCHRONA_WOD_PRZED_ZANIECZYSZCZENIEM;
    case "Podstawowe przepisy prawa drogi na morskich i śródlądowych drogach wodnych":
    case "PODSTAWOWE_PRZEPISY_PRAWA":
      return Category.PODSTAWOWE_PRZEPISY_PRAWA;
    case "Teoria żeglowania":
    case "TEORIA_ZEGLOWANIA":
      return Category.TEORIA_ZEGLOWANIA;
    default:
      console.error("Wrong value in categoryNameAsString");
      return Category.PRZEPISY;
  }
}

export enum CorrectAnswer {
  FIRST_ANSWER = "FIRST_ANSWER",
  SECOND_ANSWER = "SECOND_ANSWER",
  THIRD_ANSWER = "THIRD_ANSWER"
}

export function mapToCorrectAnswer(correctAnswerAsString: string): CorrectAnswer {
  console.log("correctAnswerAsString:", correctAnswerAsString);
  switch (correctAnswerAsString) {
    case "FIRST_ANSWER":
    case "Odpowiedź A":
      return CorrectAnswer.FIRST_ANSWER;
    case "SECOND_ANSWER":
    case "Odpowiedź B":
      return CorrectAnswer.SECOND_ANSWER;
    case "THIRD_ANSWER":
    case "Odpowiedź C":
      return CorrectAnswer.THIRD_ANSWER;
    default:
      console.error("Wrong value in correctAnswerAsString");
      return CorrectAnswer.FIRST_ANSWER;
  }
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
  id: number,
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
  correctAnswer: CorrectAnswer
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

// Exist only on frontend, created for showing validation error below input fields
export interface GroupedErrorDTO {
  fieldName: string,
  messages: string[]
}
