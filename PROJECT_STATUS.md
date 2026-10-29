# Estado do projeto — PocketWin

## Estado verificado
- Repositório: VNSANTS/PocketWin
- Branch principal: main
- Etapa: fundação inicial do projeto Android.
- Runtime Windows: **não integrado**.
- APK: **build ainda não verificado**.
- Teste em dispositivo real: ainda não realizado.

## Base técnica atual
- Aplicativo Android nativo em Java.
- Android Gradle Plugin 8.7.3.
- JDK 17, Gradle 8.9 e Android SDK 35.
- Aplicativo identifica ABI(s) do Android, versão do sistema e modelo do dispositivo.
- A interface informa explicitamente que não executa arquivos EXE nesta etapa.
- Workflow de CI: .github/workflows/android.yml.
- Commit de configuração do workflow: f09bad0df63fcbd018cbf9cf9af5f0f48aa6a6e8.

## Verificação do CI
O arquivo do workflow foi confirmado no branch main. No momento da atualização, ainda não há status de build associado ao commit do workflow. Portanto, a compilação não deve ser considerada bem-sucedida até que um run seja observado e o artefato APK seja encontrado.

## Decisões atuais
- Nome de trabalho: PocketWin.
- Não desenvolver um emulador de CPU do zero sem necessidade comprovada.
- Priorizar componentes existentes após avaliação de compatibilidade, manutenção, licenças e requisitos de ABI.
- Não exigir root para o protótipo inicial.
- Usar GitHub Actions para compilar e publicar o APK de debug como artefato.
- Tratar a compatibilidade multiplataforma como progressiva e baseada em testes, não como garantia universal.

## Próximos passos
1. Verificar o primeiro run de GitHub Actions e corrigir erros usando logs reais.
2. Baixar e instalar o APK de debug quando o artefato estiver disponível.
3. Validar inicialização e diagnóstico em pelo menos um dispositivo Android.
4. Comparar Winlator, Wine, Box64/Box86 e QEMU em termos de suporte Android, ABIs, manutenção, licenças e viabilidade de integração.
5. Escolher uma prova de conceito mínima e legalmente redistribuível para executar um programa Windows real.

## Regras de continuidade
- Atualizar este arquivo após cada etapa significativa.
- Registrar commits e resultados observados; nunca inventar resultados de testes.
- Distinguir interface demonstrativa, runtime integrado e compatibilidade efetivamente testada.
- Ao encerrar uma sessão, deixar um próximo passo concreto.
