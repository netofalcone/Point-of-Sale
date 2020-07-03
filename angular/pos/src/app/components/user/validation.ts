import { FormControl, FormGroup, Validators } from '@angular/forms';
import { isCPF, isPhone } from 'brazilian-values';
export class Validation {

  static equalsTO(field: string) {
    return (formControl: FormControl) => {
      if (!formControl.root || !(formControl.root as FormGroup).controls) {
        return null;
      }
      if (field == null) { throw new Error('É preciso inserir o campo e sua confirmação.'); }
      const fieldConfirmation = (formControl.root as FormGroup).get(field);
      if (!fieldConfirmation) { throw new Error('É preciso inserir o campo e sua confirmação.'); }
      if (fieldConfirmation.value !== formControl.value) {
        return { fieldsDoNotMatch: field };
      }
      return null;
    };
  }

  static validateCpf() {
    return (formControl: FormControl) => {
      const cpf = formControl.value;
      if (!isCPF(cpf)) { return { invalidCPF: cpf }; }
      return null;
    };
  }
  static validateFieldName() {
    return (formControl: FormControl) => {
      for (const iterator of formControl.value) {
        const regexTest = /^[A-zÀ-ú '´]+$/.test(iterator);
        if (regexTest === false) {
          return { invalidFieldName: formControl.value };
        }
      }
    }
  }
  static validateIsPhone() {
    return (formControl: FormControl) => {
      if (!isPhone(formControl.value) && formControl.value !== undefined) {
        return { invalidFieldPhone: formControl.value };
      }
    };
  }
  static validateFieldPassword() {
    return (formControl: FormControl) => {
      const regexTest = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[$*&@#])[0-9a-zA-Z$*&@!#]{8,}$/.test(formControl.value);
      if (regexTest === false) {
        return { invalidFieldPassword: formControl.value };
      }
    };
  }
  static validateFieldEmail() {
    return (formControl: FormControl) => {
      const regexTest = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+(\.[a-z]+)?$/i.test(formControl.value);
      if (regexTest === false) {
        return { invalidFieldEmail: formControl.value };
      }
    };
  }

}

