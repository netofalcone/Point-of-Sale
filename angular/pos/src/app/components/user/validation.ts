import { FormControl, FormGroup } from '@angular/forms';
import {isCPF } from 'brazilian-values';
export class Validation {

  static equalsTO(field: string) {
    return (formControl: FormControl) => {
      if (!formControl.root || !(formControl.root as FormGroup).controls) {
        return null;
      }
      if (field == null) {throw new Error('É preciso inserir o campo e sua confirmação.'); }
      const fieldConfirmation = (formControl.root as FormGroup).get(field);
      if (!fieldConfirmation) {throw new Error('É preciso inserir o campo e sua confirmação.'); }
      if (fieldConfirmation.value !== formControl.value) {
        return { fieldsDoNotMatch: field };
      } else {
        formControl.markAsTouched();
        formControl.markAsUntouched();
        fieldConfirmation.markAsTouched();
        fieldConfirmation.markAsUntouched();
      }
      return null;
    };
  }

  static validateCpf() {
    return (formControl: FormControl) => {
      const cpf = formControl.value;
      if (!isCPF(cpf)) {return { invalidCPF: cpf }; }
      return null;
    };
  }
  static validateFieldName() {
    return (formControl: FormControl) => {
        const regexTest = /[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]/.test(formControl.value);
        if (regexTest === false) {
          return { invalidFieldName: formControl.value }; }
    };
  }
}
