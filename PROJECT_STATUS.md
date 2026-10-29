# Estado do projeto — PocketWin

## Estado verificado
- Repositório: VNSANTS/PocketWin
- Branch principal: main
- Etapa: fundação inicial do projeto Android, com build automatizado confirmado.
- Runtime Windows: **não integrado**.
- APK de debug: **compilado e publicado como artefato do GitHub Actions** (run 40860585207; commit `f0fb7350fd19beb614ab208adb03438cc00cbde1`).
- Artefato: `pocketwin-debug-apk`, válido até 2026-11-12; tamanho reportado pelo GitHub: 6.289 bytes.
- Teste em dispositivo real: ainda não realizado; a geração do APK não confirma instalação nem execução no celular.

## Base técnica atual
- Aplicativo Android nativo em Java.
- Android Gradle Plugin 8.7.3.
- JDK 17, Gradle 8.9 e Android SDK 35.
- Aplicativo identifica ABI(s) do Android, versão do sistema e modelo do dispositivo.
- A interface informa explicitamente que não executa arquivos EXE nesta etapa.
- Workflow de CI: .github/workflows/android.yml.
- Pesquisa inicial de runtime: RUNTIME_RESEARCH.md.

## Verificação do CI
O run [40860585207](https://github.com/VNSANTS/PocketWin/actions/runs/40860585207) terminou com sucesso. Todas as etapas concluíram, incluindo `Assemble debug APK` e `Upload debug APK`; o artefato `pocketwin-debug-apk` foi encontrado e não está expirado. Isso confirma apenas que o protótipo Android compila e que o APK foi empacotado. Ainda não confirma instalação, estabilidade ou execução de programas Windows.

## Decisões atuais
- Nome de trabalho: PocketWin.
- Não desenvolver um emulador de CPU do zero sem necessidade comprovada.
- Priorizar componentes existentes após avaliação de compatibilidade, manutenção, licenças e requisitos de ABI.
- Não exigir root para o protótipo inicial.
- Usar GitHub Actions para compilar e publicar o APK de debug como artefato.
- Tratar a compatibilidade multiplataforma como progressiva e baseada em testes, não como garantia universal.
- Avaliar primeiro um stack derivado do Winlator para uma prova de conceito local Android; QEMU de sistema permanece uma rota experimental separada.

## Próximos passos
1. Baixar o APK de debug do [run bem-sucedido](https://github.com/VNSANTS/PocketWin/actions/runs/40860585207), instalar e validar inicialização/diagnóstico em um dispositivo Android.
2. Registrar o resultado do teste real; corrigir problemas de interface, permissões ou compatibilidade observados.
3. Inspecionar o repositório upstream escolhido, instruções de build, submódulos e licenças antes de incorporar componentes.
4. Definir uma prova de conceito mínima para executar um programa Windows real, começando por uma combinação de ABI/backend suportada e redistribuível legalmente.
5. Documentar claramente os limites de compatibilidade e manter builds automatizados.

## Regras de continuidade
- Atualizar este arquivo após cada etapa significativa.
- Registrar commits e resultados observados; nunca inventar resultados de testes.
- Distinguir interface demonstrativa, runtime integrado e compatibilidade efetivamente testada.
- Ao encerrar uma sessão, deixar um próximo passo concreto.
