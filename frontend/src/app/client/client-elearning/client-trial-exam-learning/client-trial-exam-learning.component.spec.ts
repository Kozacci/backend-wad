import { ClientTrialExamLearningComponent } from './client-trial-exam-learning.component';
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";
import {PathService} from "../../../shared/services/path.service";
import {MessageService} from "primeng/api";
import {of} from "rxjs";
import {ParticipantCourseEntityDTO, QuestionEntityDTO} from "../../../shared/dto";

describe('ClientTrialExamLearningComponent', () => {
  let component: ClientTrialExamLearningComponent;
  let restClientSpy: jasmine.SpyObj<RestClient>;
  let routeSpy: jasmine.SpyObj<ActivatedRoute>;
  let pathServiceSpy: jasmine.SpyObj<PathService>;
  let messageServiceSpy: jasmine.SpyObj<MessageService>;

  beforeEach(() => {
    restClientSpy = jasmine.createSpyObj('RestClient', ['getParticipantCourseById', 'getAllQuestionsAndDraw', 'updateTrialExamLearning']);
    routeSpy = jasmine.createSpyObj('ActivatedRoute', [], { 'params': of({ 'id': '123' }) });
    pathServiceSpy = jasmine.createSpyObj('PathService', ['navigate']);
    messageServiceSpy = jasmine.createSpyObj('MessageService', ['add']);

    component = new ClientTrialExamLearningComponent(restClientSpy, routeSpy, pathServiceSpy, messageServiceSpy);
  });

  it('should initialize and start timer on ngOnInit', () => {
    // Mock data for courses and questions
    const mockParticipantCourse: ParticipantCourseEntityDTO = {
      participantCourseId: 1,
      courseId: 101,
      courseType: 'Typ Kursu',
      courseDateFrom: new Date('2023-01-01'),
      courseDateTo: new Date('2023-01-10'),
      accessDate: new Date('2023-01-01'),
      participantId: 1001,
      participantEmail: 'test@example.com',
      participantLastName: 'Testowy',
      isPassed: false,
      isPaid: true,
      onlinePayment: true
    };

    const mockQuestions: QuestionEntityDTO[] = [
      {
        questionId: 1,
        content: 'Jakie jest pytanie numer 1?',
        category: 'Kategoria 1',
        firstAnswer: 'Odpowiedź A',
        secondAnswer: 'Odpowiedź B',
        thirdAnswer: 'Odpowiedź C',
        correctAnswer: 'Odpowiedź A',
        explanation: 'Wyjaśnienie odpowiedzi A',
        image: 'image1.jpg'
      },
      {
        questionId: 2,
        content: 'Jakie jest pytanie numer 2?',
        category: 'Kategoria 2',
        firstAnswer: 'Odpowiedź D',
        secondAnswer: 'Odpowiedź E',
        thirdAnswer: 'Odpowiedź F',
        correctAnswer: 'Odpowiedź E',
      }
    ];

    restClientSpy.getParticipantCourseById.and.returnValue(of(mockParticipantCourse));
    restClientSpy.getAllQuestionsAndDraw.and.returnValue(of(mockQuestions));

    spyOn(component, 'startTimer');

    component.ngOnInit();

    expect(component.participantCourse).toEqual(mockParticipantCourse);
    expect(component.randomQuestions).toEqual(mockQuestions);
    expect(component.startTimer).toHaveBeenCalled();
    expect(messageServiceSpy.add).toHaveBeenCalled();
  });

  it('should finish the exam and call the updateTrialExamLearning with the correct result', () => {
    const trialExam = {
      id: 5,
      total: 4,
      passed: 3,
      failed: 1
    }
    component.participantCourseId = 5;
    component.totalQuestions = 4
    component.totalPassedQuestions = 3;
    restClientSpy.updateTrialExamLearning.and.returnValue(of(trialExam));

    component.finishTrialExam(trialExam.id);

    expect(component.percentage).toBe(75);
    expect(restClientSpy.updateTrialExamLearning).toHaveBeenCalledWith(trialExam.id, true);
    expect(component.isFinished).toBeTrue();
  });

});
