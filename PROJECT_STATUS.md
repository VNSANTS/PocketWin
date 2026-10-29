# Estado do projeto — PocketWin

## Estado verificado

- Repositório: `VNSANTS/PocketWin`
- Branch principal: `main`
- Etapa: fundação inicial do projeto Android.
- Objetivo: aplicativo Android que hospede um ambiente de compatibilidade para programas Windows, com suporte progressivo a diferentes arquiteturas.
- Runtime Windows: **não integrado**.
- APK: ainda precisa ser validado pelo primeiro build automatizado.

## Decisões atuais

- Nome de trabalho: PocketWin.
- Interface nativa Android em Java para manter o primeiro protótipo simples.
- Android Gradle Plugin 8.7.3, Gradle 8.9, JDK 17 e SDK Android 35.
- GitHub Actions como pipeline inicial de build.
- Não desenvolver emulador de CPU do zero sem uma necessidade comprovada.
- Priorizar componentes existentes após avaliação de compatibilidade, manutenção, licenças e requisitos de ABI.

## Próximos passos

1. Executar o workflow de build e corrigir qualquer erro real.
2. Instalar o APK de debug em pelo menos um dispositivo Android.
3. Registrar arquitetura da CPU, versão Android, RAM e GPU de dispositivos de teste.
4. Fazer uma avaliação técnica comparativa de Wine, Box64/Box86, Winlator e QEMU.
5. Definir um teste mínimo de execução de um programa Windows antes de escolher a integração definitiva.

## Regras de continuidade

- Atualizar este arquivo ao concluir uma etapa significativa.
- Registrar os commits e os resultados observados; não inventar resultados de testes.
- Distinguir claramente interface demonstrativa, runtime integrado e compatibilidade efetivamente testada.
- Se uma sessão terminar, deixar aqui o próximo passo concreto.
