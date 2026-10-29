# Roadmap — PocketWin

## Fase 1 — Fundação Android
- [x] Criar estrutura inicial do projeto.
- [x] Adicionar documentação de arquitetura e continuidade.
- [x] Adicionar workflow de build automatizado de debug.
- [x] Registrar pesquisa inicial de runtimes em RUNTIME_RESEARCH.md.
- [x] Confirmar que o workflow termina com sucesso (run 40860585207; APK publicado como artefato).
- [ ] Instalar e abrir o APK em um dispositivo real.

**Saída exigida:** APK instalável e inicialização verificada.

## Fase 2 — Diagnóstico de dispositivo
- [x] Exibir ABI(s), versão do Android e modelo na interface inicial.
- [x] Adicionar RAM total/disponível por APIs públicas do Android.
- [ ] Adicionar informações gráficas/hardware adicionais quando acessíveis e confiáveis.
- [x] Criar relatório de diagnóstico compartilhável em texto.
- [ ] Validar o relatório exportado em dispositivo real.

**Saída exigida:** relatório verificável de compatibilidade do dispositivo.

## Fase 3 — Prova de conceito do runtime
- [ ] Inspecionar um upstream específico, tags/releases, submódulos e instruções de build.
- [ ] Verificar licenças e obrigações de redistribuição de todas as dependências.
- [ ] Escolher um alvo mínimo de aplicação Windows.
- [ ] Integrar um backend compatível com Android.
- [ ] Executar e documentar um primeiro programa de teste autorizado.

**Saída exigida:** aplicação Windows real executada, não apenas uma tela simulada.

## Fase 4 — Gerenciamento de ambientes e processos
- [ ] Criar e remover ambientes isolados.
- [ ] Importar instaladores compatíveis.
- [ ] Exibir progresso, logs e mensagens de erro.
- [ ] Implementar backup e limpeza segura dos dados do ambiente.
- [ ] Criar gerenciador de processos do runtime Windows com lista atualizável, seleção individual e opção “Selecionar todos os aplicativos”.
- [ ] Excluir automaticamente processos essenciais do Windows/runtime da seleção em lote; permitir encerrar somente processos de aplicativos identificados com segurança.
- [ ] Mostrar aviso e confirmação antes de encerrar processos, e atualizar a estimativa de RAM após a ação; nunca prometer liberação exata de memória.
- [ ] Restringir o encerramento aos processos gerenciados pelo ambiente PocketWin; não tentar encerrar processos do Android ou de outros aplicativos.

## Fase 5 — Compatibilidade multi-arquitetura
- [ ] Detectar ABI do Android e requisitos do executável.
- [ ] Separar backends por arquitetura.
- [ ] Testar aplicações x86, x64 e ARM individualmente.
- [ ] Avaliar tradução binária e fallback remoto quando necessário.

**Observação:** não existe garantia de compatibilidade universal. A compatibilidade será declarada por combinação de dispositivo, backend e aplicação testada.

## Fase 6 — Desempenho, segurança e UX
- [ ] Perfil de desempenho e limites térmicos/energéticos.
- [ ] Controles de armazenamento e isolamento.
- [ ] Interface adaptada a toque, teclado e mouse.
- [ ] Matriz de dispositivos e testes de regressão.
- [ ] Revisão de licenças antes de qualquer distribuição pública.

## Regra de conclusão
Nenhuma fase será considerada concluída sem evidência adequada. Um build verde prova compilação; não prova execução de programas Windows nem compatibilidade universal.
