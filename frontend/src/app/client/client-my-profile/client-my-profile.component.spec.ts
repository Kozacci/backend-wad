import {ClientMyProfileComponent} from './client-my-profile.component';
import {RestClient} from "../../shared/rest-client";
import {MessageService} from "primeng/api";
import {FormService} from "../../shared/services/form/form.service";
import {of} from "rxjs";
import {Role} from "../../shared/dto";

/**
 * Jasmine Unit Test to check my profile component
 */
describe('ClientMyProfileComponent', () => {
  let component: ClientMyProfileComponent;
  let formServiceSpy: jasmine.SpyObj<FormService>;
  let restClientSpy: jasmine.SpyObj<RestClient>;
  let messageServiceSpy: jasmine.SpyObj<MessageService>;

  beforeEach(() => {
    restClientSpy = jasmine.createSpyObj('RestClient', ['updateParticipant']);
    messageServiceSpy = jasmine.createSpyObj('MessageService', ['add']);
    component = new ClientMyProfileComponent(formServiceSpy, restClientSpy, messageServiceSpy);

    spyOn(sessionStorage, 'getItem').and.callFake((key) => {
      switch (key) {
        case 'cacheId': return '123';
        default: return null;
      }
    });
    spyOn(sessionStorage, 'setItem');
  });

  /**
   *  It checks if updating user profile works properly
   *  by setting new form values
   *  by checking if pToast has been successfully printed
   *  by checking if session storage got updated
   */
  it('should call updateParticipant and update sessionStorage on success', () => {
    component.formGroup.setValue({
      firstName: 'Test',
      lastName: 'User',
      email: 'test@example.com',
      phoneNumber: '123456789'
    });

    const mockResponse = {
      id: 1,
      createdAt: new Date(),
      firstName: 'Test',
      lastName: 'User',
      email: 'testUser@op.com',
      password: 'testUser123',
      phoneNumber: '123456789',
      courses: [],
      role: Role.CLIENT
    };
    restClientSpy.updateParticipant.and.returnValue(of(mockResponse));

    component.updateParticipant();

    expect(restClientSpy.updateParticipant).toHaveBeenCalledWith(123, jasmine.any(Object));
    expect(messageServiceSpy.add).toHaveBeenCalledWith(jasmine.objectContaining({
      severity: 'success',
      summary: 'Edycja',
      detail: 'Udało Ci się poprawnie zmienić dane osobowe'
    }));
    expect(sessionStorage.setItem).toHaveBeenCalledWith('cacheFirstName', 'Test');
    expect(sessionStorage.setItem).toHaveBeenCalledWith('cacheLastName', 'User');
    expect(sessionStorage.setItem).toHaveBeenCalledWith('cachePhoneNumber', '123456789');
  });

});
