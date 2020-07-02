export class ValidationMessage {

  static getErrorMessage(validatorName: string, validatorValue?: any) {
    const errorMessage = {
      required: `Campo obrigatório.`,
      email: `E-mail inválido.`,
      minlength: `O campo precisa ter no mínimo ${validatorValue.requiredLength} caracteres.`,
      maxlength: `O campo precisa ter no máximo ${validatorValue.requiredLength} caracteres.`,
      fieldsDoNotMatch: `Campos não coincidem`,
      invalidCPF: `CPF inválido.`,
      emptyField: `Valores vázios não são aceitos.`,
      invalidFieldName: `O nome não deve possuir numeros ou caracteres especiais`
    };
    return errorMessage[validatorName];
  }
}
