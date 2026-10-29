# PocketWin — transferência de contexto para Codex

Leia este documento e os arquivos de projeto antes de modificar qualquer coisa.

## Projeto
- Repositório: https://github.com/VNSANTS/PocketWin
- Branch: main
- App Android nativo em Java, nome PocketWin.
- Objetivo: criar progressivamente um ambiente Android que execute aplicações Windows compatíveis, investigando runtimes existentes.
- O usuário autorizou decisões técnicas proativas. Não pergunte sobre escolhas pequenas; avance e explique as decisões.
- Use GitHub Actions para builds e mantenha a documentação atualizada.

## Estado real
O app é somente uma fundação de interface/diagnóstico. **Ainda não existe runtime Windows integrado e não foi demonstrada execução de EXE.** Build do APK está confirmado no GitHub Actions; teste em dispositivo ainda não foi realizado.

Leia: `README.md`, `PROJECT_STATUS.md`, `ROADMAP.md`, `ARCHITECTURE.md`, `RUNTIME_RESEARCH.md` e `CONTRIBUTING.md` se existir. Confirme o conteúdo atual antes de editar.

Base técnica conhecida:
- `app/src/main/java/com/pocketwin/MainActivity.java`
- Android Gradle Plugin 8.7.3; JDK 17; Gradle 8.9; compileSdk 35; minSdk 26; targetSdk 35.
- Workflow: `.github/workflows/android.yml`
- APK esperado: `app/build/outputs/apk/debug/app-debug.apk`
- Artefato esperado: `pocketwin-debug-apk`

## CI: concluído — não repetir correções sem necessidade
Workflow: https://github.com/VNSANTS/PocketWin/blob/main/.github/workflows/android.yml

Histórico:
1. Builds antigos falhavam ao instalar o pacote legado `tools` com `android-actions/setup-android@v3`.
2. O workflow duplicado `.github/workflows/android-build.yml` foi removido.
3. Depois, o comando `sdkmanager` não era encontrado no PATH.
4. O workflow passou a localizar o SDK manager em `$ANDROID_HOME/cmdline-tools`.
5. Run no commit `e9c01455010c20e13963ac26e8c74b2cef41f90e` localizou o SDK manager e aceitou as licenças, mas falhou com `yes: standard output: Broken pipe`, pois `yes | sdkmanager --licenses` era executado com `pipefail`.
6. O commit `3447170a38c8bdd7b67c408734e7cfcd7547f8d8` alterou o passo para usar `PIPESTATUS` e preservar o status do sdkmanager.

Run confirmado com sucesso: https://github.com/VNSANTS/PocketWin/actions/runs/40860585207
- Job `Build debug APK`: sucesso.
- Etapas `Assemble debug APK` e `Upload debug APK`: sucesso.
- Artefato `pocketwin-debug-apk`: listado, não expirado, disponível até 2026-11-12.
- Commit compilado: `f0fb7350fd19beb614ab208adb03438cc00cbde1`.
- O run anterior `40860407043` também terminou em sucesso.

O problema do pipeline foi resolvido. Não volte a alterar o workflow sem evidência de um novo defeito. O `PROJECT_STATUS.md` já registra o build verificado.

## Depois do build
- Compartilhe o link do run/artefato para baixar o APK.
- O usuário instala e abre no Android; registre o resultado e corrija problemas de inicialização antes de ampliar o escopo.
- Não avance alegando que o runtime funciona antes de executar um programa Windows real.

## Direção técnica do runtime
A pesquisa inicial em `RUNTIME_RESEARCH.md` aponta Winlator como candidato a POC Android (Wine + componentes de tradução de CPU/gráficos), mas compatibilidade depende da ABI, GPU/backend, versão e app. QEMU user-mode Linux/BSD não substitui sozinho um runtime Windows; QEMU de sistema completo é uma rota diferente e pesada.

Antes de incorporar componentes externos, inspecione o upstream oficial, requisitos de build, submódulos e licenças de cada componente, além das obrigações de redistribuição. Não copie/distribua sem confirmar permissão. Não crie emulador de CPU do zero sem motivo comprovado. Não prometa compatibilidade universal x86/x64/ARM; teste combinações reais.

## Regras de trabalho
- Manter Android sem root como objetivo inicial.
- Diferenciar funcionalidades implementadas, planejadas e testadas.
- Não criar botões ou telas que finjam executar EXEs.
- Fazer alterações pequenas e verificáveis; executar build/testes quando possível.
- Atualizar `PROJECT_STATUS.md` e documentos relacionados após cada etapa.
- Nunca inventar resultados de build, instalação ou testes.

## Critério de conclusão da etapa CI — concluída
- [x] Workflow termina em sucesso.
- [x] APK debug é gerado.
- [x] Artefato `pocketwin-debug-apk` está disponível.
- [x] `PROJECT_STATUS.md` registra commit e resultado verificados.
- [ ] Instalação e teste real no Android.
- [ ] POC de runtime e execução comprovada de um programa Windows.
