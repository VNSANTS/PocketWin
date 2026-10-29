# Arquitetura — PocketWin

## Princípio

PocketWin terá uma interface Android própria e uma camada de integração para runtimes externos. A interface não deve assumir que todos os dispositivos conseguem executar o mesmo backend.

## Camadas propostas

1. **UI Android:** biblioteca de ambientes, importação de instaladores, estado do runtime, logs e configurações.
2. **Gerenciador de ambientes:** diretórios, configurações por ambiente, ciclo de vida e limpeza.
3. **Detector de capacidades:** ABI do Android, versão do sistema, RAM disponível e informações gráficas acessíveis por APIs públicas.
4. **Adaptador de runtime:** interface interna para iniciar, parar e consultar o backend selecionado.
5. **Backend de compatibilidade:** inicialmente será escolhido após uma prova de conceito. Wine fornece APIs de compatibilidade Windows; Box64/Box86 podem traduzir executáveis de determinadas arquiteturas; QEMU oferece emulação/virtualização em cenários específicos.
6. **Camada nativa por ABI:** bibliotecas compiladas para as ABIs Android suportadas. Binários nativos incompatíveis não podem ser carregados diretamente.

## Distinções técnicas essenciais

- Wine não é uma implementação completa do kernel Windows nem executa todos os programas.
- Tradução de CPU e compatibilidade de APIs são problemas diferentes; ambos podem ser necessários.
- Virtualização acelerada depende de suporte de hardware, kernel e permissões do dispositivo.
- Emulação de uma arquitetura diferente pode ter custo alto de CPU, memória e bateria.
- Drivers Windows arbitrários não podem ser presumidos funcionais no Android.
- Um APK universal pode conter código comum, mas bibliotecas nativas ainda podem precisar de builds por ABI.

## Estratégia de implementação

Não escrever um emulador de CPU próprio como primeiro passo. Fazer uma prova de conceito com um runtime existente, documentar licenças e limitações e encapsular sua integração por uma interface interna para permitir substituir backends.

## Segurança

- Tratar executáveis importados como código não confiável.
- Não solicitar root como requisito do produto.
- Não executar comandos arbitrários recebidos de instaladores.
- Isolar arquivos do ambiente sempre que possível e oferecer remoção explícita dos dados.
- Explicar que um runtime de compatibilidade não equivale a uma sandbox de segurança perfeita.
- Não incluir tokens, senhas ou chaves no repositório.

## Build

O workflow de CI compila apenas o aplicativo Android inicial. A integração de um runtime nativo terá requisitos adicionais de compilação, ABI, tamanho do APK, licenciamento e testes.
