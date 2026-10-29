# PocketWin

PocketWin é um projeto experimental para Android que pretende oferecer uma interface única para executar programas Windows por meio de runtimes de compatibilidade e backends adequados à arquitetura do dispositivo.

> **Estado atual:** fundação do aplicativo Android. O runtime Windows ainda não está integrado. O primeiro APK é um protótipo de interface, não um emulador funcional.

## Objetivos

- Um aplicativo Android com interface própria.
- Detectar a arquitetura e os recursos do dispositivo.
- Investigar integração de runtimes existentes, priorizando componentes mantidos e licenciados adequadamente.
- Evoluir progressivamente a compatibilidade com aplicações Windows x86, x64 e ARM quando tecnicamente viável.
- Automatizar builds de debug pelo GitHub Actions.

## Requisitos de desenvolvimento

- JDK 17
- Android SDK com plataforma Android 35
- Gradle 8.9
- Android Gradle Plugin 8.7.3

O GitHub Actions instala as dependências necessárias e gera o APK de debug.

## Build local

Com JDK 17, Android SDK configurado e Gradle 8.9 instalado:

```bash
gradle assembleDebug
```

APK esperado: `app/build/outputs/apk/debug/app-debug.apk`.

## Documentação de continuidade

1. [PROJECT_STATUS.md](PROJECT_STATUS.md)
2. [ROADMAP.md](ROADMAP.md)
3. [ARCHITECTURE.md](ARCHITECTURE.md)
4. [RUNTIME_RESEARCH.md](RUNTIME_RESEARCH.md)
5. [CONTRIBUTING.md](CONTRIBUTING.md)

Não considere uma funcionalidade concluída sem evidência de build ou teste. O runtime de compatibilidade será uma etapa separada; não se deve afirmar que programas Windows funcionam até que isso seja demonstrado.

## Licenciamento

O código inicial deste repositório ainda não define uma licença de distribuição. Antes de incorporar Wine, Box64/Box86, bibliotecas gráficas ou outros componentes, verificar suas licenças, obrigações de distribuição e compatibilidade.
