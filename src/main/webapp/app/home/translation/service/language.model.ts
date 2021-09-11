export class Language {
  constructor(public language: string, public name: string) {}
}

export class Languages {
  constructor(public languages: Language[]) {}
}
