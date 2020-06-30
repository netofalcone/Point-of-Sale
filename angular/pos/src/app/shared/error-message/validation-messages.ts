export class ValidationMessage {

  static getErrorMessage(validatorName: string, validatorValue?: any) {
    const errorMessage = {
      'required':`Campo obrigatório.`,
      'email':`E-mail inválido.`,
      'minlength':`O campo precisa ter no mínimo ${validatorValue.requiredLength} caracteres.`,
      'maxlength':`O campo precisa ter no máximo ${validatorValue.requiredLength} caracteres.`
    };
    return errorMessage[validatorName];
  }
}
