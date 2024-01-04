import {ClientGeneralLearningComponent} from './client-general-learning.component';
import {RestClient} from "../../../shared/rest-client";
import {ActivatedRoute} from "@angular/router";
import {ClientElearningService} from "../client-elearning.service";
import {of} from "rxjs";
import {Category, CorrectAnswer, CourseType} from "../../../shared/dto";

/**
 * Jasmine Unit Test to check client general learning component
 */
describe('ClientGeneralLearningComponent', () => {
  let component: ClientGeneralLearningComponent;
  let restClientSpy: jasmine.SpyObj<RestClient>;
  let routeSpy: jasmine.SpyObj<ActivatedRoute>;
  let clientElearningServiceSpy: jasmine.SpyObj<ClientElearningService>;

  beforeEach(() => {
    restClientSpy = jasmine.createSpyObj('RestClient', ['getParticipantCourseById', 'getRandomQuestionByCategories', 'updateGeneralLearning']);
    routeSpy = jasmine.createSpyObj('ActivatedRoute', [], { 'params': of({ 'id': 123 }) });
    clientElearningServiceSpy = jasmine.createSpyObj('ClientElearningService', ['getCategoriesByCourseType']);

    component = new ClientGeneralLearningComponent(restClientSpy, routeSpy, clientElearningServiceSpy);
  });

  /**
   *  It checks if on initialization there is participant course and random question loaded
   *  by mocking variables and asserting them to component's attributes after loading all data
   */
  it('should get participant course and random question on init', () => {
    const mockCourse = {
      participantCourseId: 123,
      courseId: 122,
      courseType: CourseType.STERNIK_MOTOROWODNY,
      courseDateFrom: new Date(),
      courseDateTo: new Date(),
      accessDate: new Date(),
      participantId: 121,
      participantEmail: 'test@op.pl',
      participantLastName: 'Test',
      isPassed: false,
      isPaid: false,
      onlinePayment: false
    };

    const mockQuestion = {
      questionId: 123,
      content: 'test',
      category: Category.POMOCE_NAWIGACYJNE,
      firstAnswer: 'test',
      secondAnswer:'test',
      thirdAnswer: 'test',
      correctAnswer: CorrectAnswer.FIRST_ANSWER,
      explanation: 'test',
      image: 'test',
    };

    clientElearningServiceSpy.getCategoriesByCourseType.and.returnValue([Category.POMOCE_NAWIGACYJNE, Category.WIADOMOSCI_Z_METEOROLOGII]);
    restClientSpy.getParticipantCourseById.and.returnValue(of(mockCourse));
    restClientSpy.getRandomQuestionByCategories.and.returnValue(of(mockQuestion));

    component.ngOnInit();

    expect(clientElearningServiceSpy.getCategoriesByCourseType).toHaveBeenCalledWith(mockCourse);
    expect(restClientSpy.getParticipantCourseById).toHaveBeenCalledWith(123);
    expect(restClientSpy.getRandomQuestionByCategories).toHaveBeenCalledWith([Category.POMOCE_NAWIGACYJNE, Category.WIADOMOSCI_Z_METEOROLOGII]);
    expect(component.participantCourse).toEqual(mockCourse);
    expect(component.randomQuestion).toEqual(mockQuestion);
  });

  /**
   *  It checks if selection of answer works properly
   *  by mocking simple string into answer, selecting it and checking if it is equals to component's attribute
   */
  it('should update selectedAnswer when selectAnswer is called', () => {
    const answer = 'testAnswer';
    component.selectAnswer(answer);
    expect(component.selectedAnswer).toEqual(answer);
  });

  /**
   *  It checks if selection of correct answer works properly
   *  by mocking string into correct answer, selecting it and checking if update of answer history is set to true
   */
  it('should call updateGeneralLearning with true for correct answer', () => {
    component.randomQuestion.correctAnswer = 'correctAnswer';
    component.selectAnswer('correctAnswer');
    spyOn(component, 'updateGeneralLearning');

    component.checkAnswer();

    expect(component.updateGeneralLearning).toHaveBeenCalledWith(component.participantCourseId, true);
    expect(component.submitted).toBeTrue();
  });

  /**
   *  It checks if failure selection of correct answer works properly
   *  by mocking string into correct answer, selecting it and checking if update of answer history is set to false
   */
  it('should call updateGeneralLearning with false for incorrect answer', () => {
    component.randomQuestion.correctAnswer = 'correctAnswer';
    component.selectAnswer('incorrectAnswer');
    spyOn(component, 'updateGeneralLearning');

    component.checkAnswer();

    expect(component.updateGeneralLearning).toHaveBeenCalledWith(component.participantCourseId, false);
    expect(component.submitted).toBeTrue();
  });

});
