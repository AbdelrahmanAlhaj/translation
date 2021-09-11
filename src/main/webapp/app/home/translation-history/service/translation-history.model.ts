export class TranslationHistory {
  constructor(
    public id?: number,
    public text?: string,
    public translatedText?: string,
    public sourceLanguage?: string,
    public targetLanguage?: string,
    public rank?: number,
    public createdBy?: string,
    public createdDate?: Date,
    public lastModifiedBy?: string,
    public lastModifiedDate?: Date,
    public isSelected?: boolean
  ) {}
}
