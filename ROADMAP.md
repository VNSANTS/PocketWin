# Roadmap — PocketWin

## Fase 1 — Fundação Android
- [x] Criar estrutura inicial do projeto.
- [x] Adicionar documentação de arquitetura e continuidade.
- [x] Configurar build automatizado de debug.
- [ ] Confirmar que o workflow termina com sucesso.
- [ ] Instalar e abrir o APK em um dispositivo real.

**Saída exigida:** APK instalável e inicialização verificada.

## Fase 2 — Diagnóstico de dispositivo
- [ ] Detectar ABI/arquitetura, versão do Android, memória e recursos disponíveis.
- [ ] Exibir os dados no aplicativo sem solicitar permissões desnecessárias.
- [ ] Criar relatório de diagnóstico exportável.

**Saída exigida:** relatório verificável de compatibilidade do dispositivo.

## Fase 3 — Prova de conceito do runtime
- [ ] Comparar soluções existentes e suas licenças.
- [ ] Escolher um alvo mínimo de aplicação Windows.
- [ ] Integrar um backend compatível com Android.
- [ ] Executar e documentar um primeiro programa de teste autorizado.

**Saída exigida:** aplicação Windows real executada, não apenas uma tela simulada.

## Fase 4 — Gerenciamento de ambientes
- [ ] Criar e remover ambientes isolados.
- [ ] Importar instaladores compatíveis.
- [ ] Exibir progresso, logs e mensagens de erro.
- [ ] Implementar backup e limpeza segura dos dados do ambiente.

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
