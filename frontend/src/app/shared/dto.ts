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

export function mapToCourseStatus(statusAsString: string): CourseStatus {
  console.log("statusAsString:", statusAsString);
  switch (statusAsString) {
    case "Nierozpoczęty":
    case "NIEROZPOCZETY":
      return CourseStatus.NIEROZPOCZETY;
    case "Rozpoczęty":
    case "ROZPOCZETY":
      return CourseStatus.ROZPOCZETY;
    case "Zakończony":
    case "ZAKONCZONY":
      return CourseStatus.ZAKONCZONY;
    default:
      console.error("Nieprawidłowa wartość w statusAsString");
      return CourseStatus.NIEROZPOCZETY; // Domyślna wartość lub inna akcja
  }
}

export enum CourseCity {
  SOPOT = "SOPOT",
  OLECKO = "OLECKO"
}

export function mapToCourseCity(cityAsString: string): CourseCity {
  console.log("cityAsString:", cityAsString);
  switch (cityAsString) {
    case "Sopot":
    case "SOPOT":
      return CourseCity.SOPOT;
    case "Olecko":
    case "OLECKO":
      return CourseCity.OLECKO;
    default:
      console.error("Nieprawidłowa wartość w cityAsString");
      return CourseCity.SOPOT; // Domyślna wartość lub inna akcja
  }
}

export enum CourseType {
  STERNIK_MOTOROWODNY = "STERNIK_MOTOROWODNY",
  JACHTOWY_STERNIK_MORSKI = "JACHTOWY_STERNIK_MORSKI",
  MOTOROWODNY_STERNIK_MORSKI = "MOTOROWODNY_STERNIK_MORSKI",
  ZEGLARZ_JACHTOWY = "ZEGLARZ_JACHTOWY",
  WARSZTATY_NAWIGACYJNE = "WARSZTATY_NAWIGACYJNE",
  REJSY_STAZOWE = "REJSY_STAZOWE"
}

export function mapToCourseType(courseNameAsString: string): CourseType {
  console.log("courseNameAsString:", courseNameAsString);
  switch (courseNameAsString) {
    case "Sternik motorowodny":
    case "STERNIK_MOTOROWODNY":
      return CourseType.STERNIK_MOTOROWODNY;
    case "Jachtowy sternik morski":
    case "JACHTOWY_STERNIK_MORSKI":
      return CourseType.JACHTOWY_STERNIK_MORSKI;
    case "Motorowodny sternik morski":
    case "MOTOROWODNY_STERNIK_MORSKI":
      return CourseType.MOTOROWODNY_STERNIK_MORSKI;
    case "Żeglarz jachtowy":
    case "ZEGLARZ_JACHTOWY":
      return CourseType.ZEGLARZ_JACHTOWY;
    case "Warsztaty nawigacyjne":
    case "WARSZTATY_NAWIGACYJNE":
      return CourseType.WARSZTATY_NAWIGACYJNE;
    case "Rejsy stażowe":
    case "REJSY_STAZOWE":
      return CourseType.REJSY_STAZOWE;
    default:
      console.error("Nieprawidłowa wartość w courseNameAsString");
      return CourseType.STERNIK_MOTOROWODNY; // Możesz tutaj zdecydować o domyślnej wartości lub innej akcji.
  }
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

export interface ParticipantEventEntityCreateDTO {
  eventId: number,
  ordererEmail: string,
  ordererFirstName: string,
  ordererLastName: string,
  ordererPhoneNumber: string,
  participantsNumber: number
}

export interface ParticipantEntityDTO {
  id: number,
  createdAt: Date,
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

export interface CourseCreateUpdateDTO {
  courseType: CourseType,
  dateFrom: string | null,
  dateTo: string | null,
  city: CourseCity,
  maxParticipantsNumber: number
}

export interface CourseEntityDTO {
  id: number,
  courseType: string,
  dateFrom: Date,
  dateTo: Date,
  courseStatus: string,
  maxParticipantsNumber: number,
  assignedParticipantsNumber: number,
  city: string
}

export interface EventEntityDTO {
  id: number,
  type: string,
  cost: number,
  date: Date,
  city: string,
  duration: Date,
  assignedParticipants: number,
  maxParticipantsNumber: number
}

export interface EventFilterDTO {
  id: number,
  type: string,
  city: string,
  cost: number,
  assignedParticipants: number,
  maxParticipantsNumber: number,
  duration: Date,
  ordererLastName: string,
  ordererEmail: string
}

export interface ErrorDTO {
  fieldName: string,
  message: string
}

/**  ======== ONLY FRONTEND ======== **/

// created for showing validation error below input fields
export interface GroupedErrorDTO {
  fieldName: string,
  messages: string[]
}

// added to avoid using '{name: string, value: string} | null' everywhere
export type NameValueNull = {name: string, value: string} | null;

/** if you want to use this type in pDropdown, example here:
 *
 * code in example.component.ts:
  courseTypes = [
    { name: "STERNIK_MOTOROWODNY", value: "Sternik motorowodny"},
    { name: "JACHTOWY_STERNIK_MORSKI", value: "Jachtowy sternik morski"},
    { name: "MOTOROWODNY_STERNIK_MORSKI", value: "Motorowodny sternik morski"},
    { name: "ZEGLARZ_JACHTOWY", value: "Żeglarz jachtowy"},
    { name: "WARSZTATY_NAWIGACYJNE", value: "Warsztaty nawigacyjne"},
    { name: "REJSY_STAZOWE", value: "Rejsy stażowe"},
  ];

 * code in example.component.html:
 * <p-dropdown
 *   [options]="courseTypes"
 *   [(ngModel)]="courseTypeToAdd"
 *   optionLabel="value"
 *   placeholder="Typ kursu"
 *   [showClear]="true">
 * </p-dropdown>

 string declared in optionLabel field will be shown at site
 so in this example options visible at site will be:
 1. Sternik motorowodny, 2. Jachtowy sternik morski, 3. Motorowodny sternik morski
 4. Żeglarz jachtowy, 5. Warsztaty nawigacyjne, 6. Rejsy stażowe

**/
