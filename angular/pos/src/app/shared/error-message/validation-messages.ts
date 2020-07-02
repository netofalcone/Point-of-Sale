export class ValidationMessage {

  static getErrorMessage(validatorName: string, validatorValue?: any) {
    const errorMessage = {
      required: `Campo obrigatório.`,
      email: `E-mail inválido.`,
      minlength: `O campo precisa ter no mínimo ${validatorValue.requiredLength} caracteres.`,
      maxlength: `O campo precisa ter no máximo ${validatorValue.requiredLength} caracteres.`,
      fieldsDoNotMatch: `Campos de senha não coincidem`,
      invalidCPF: `CPF inválido.`,
      emptyField: `Valores vázios não são aceitos.`,
      invalidFieldName: `O nome não deve possuir numeros ou caracteres especiais`,
      invalidFieldPhone : `Formato de telefone inválido, são aceitos somente caracteres numéricos.`,
      invalidFieldPassword: `Formato de senha inválido, deve conter ao menos um dígito, uma letra minúscula, uma letra maiúscula, um caractere especial.`
    };
    return errorMessage[validatorName];
  }
}
