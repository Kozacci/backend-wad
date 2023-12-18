import {Pipe, PipeTransform} from '@angular/core';
import {CorrectAnswer} from "../dto";

@Pipe({
  name: 'correctAnswer'
})
export class CorrectAnswerPipe implements PipeTransform {

  transform(value: CorrectAnswer): string {
    switch (value) {
      case CorrectAnswer.FIRST_ANSWER:
        return 'Odpowiedź A';
      case CorrectAnswer.SECOND_ANSWER:
        return 'Odpowiedź B';
      case CorrectAnswer.THIRD_ANSWER:
        return 'Odpowiedź C';
      default:
        return 'Nie wskazano'
    }
  }

}
