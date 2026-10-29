# Runtime strategy research — PocketWin

**Status:** Initial source review; no runtime integration decision is final until source/build/license review is complete.

## Findings

### Winlator: strongest first candidate for a local Android proof of concept
The Winlator project describes running Windows x86_64 applications on Android using Wine and Box86/Box64, with graphics components such as Mesa and DXVK/VKD3D in relevant builds.

Sources:
- Official website: https://www.winlator.org/
- Upstream-style repository reference: https://github.com/winebox64/winlator

Advantages:
- It is already designed around Android lifecycle, containers, input, and graphics integration.
- Reuses established Windows API compatibility and CPU translation projects.
- Provides a more realistic starting point than creating a compatibility stack from scratch.

Risks:
- Many existing builds target ARM64 Android devices and do not automatically support x86/x86_64 Android hosts.
- Graphics performance depends on GPU drivers, Vulkan support, and device-specific paths.
- Source availability does not automatically grant permission to redistribute every bundled component; each dependency and asset needs license review.
- Integrating the entire codebase may be more complex than using a documented runtime boundary.

### QEMU: broad CPU and machine emulation, but not a universal Windows-app shortcut
QEMU supports a wide range of guest CPU architectures. Its system-emulation mode models a whole machine, while user-mode emulation launches programs built for another CPU architecture under the same operating-system family.

Sources:
- QEMU emulation overview: https://www.qemu.org/docs/master/about/emulation.html
- QEMU user-mode emulation: https://www.qemu.org/docs/master/user/

Important limitation:
QEMU user-mode support is documented for Linux and BSD user-space programs; it is not a direct replacement for Wine when the target is a Windows executable. Booting a complete Windows guest with system emulation is a separate route with much higher resource and performance costs.

### Wine and CPU translation solve different problems
Wine implements Windows API compatibility. Box86/Box64 translate supported x86/x86_64 instructions for supported host architectures. Neither alone guarantees compatibility with every Windows application. Graphics APIs, drivers, .NET components, Windows services, and application-specific behavior remain separate constraints.

## Recommended direction

1. Keep PocketWin's Android UI independent from any one runtime.
2. For the first local proof of concept, evaluate integrating or adapting a maintained Winlator-derived stack on an ARM64 Android device.
3. Do not claim support for x86/x86_64 Android phones until a native build and real test on such a host exists.
4. Treat QEMU full-system emulation as a separate experimental backend, not the default execution path.
5. Define compatibility by tested tuples: Android version + host ABI + GPU/backend + runtime version + Windows application.
6. Before copying or bundling upstream source/binaries, review each repository's license, submodules, third-party notices, build instructions, and redistribution requirements.

## Decision gate before implementation
Do not vendor a runtime until we have:
- identified the authoritative upstream repository and release/tag;
- confirmed its license and third-party redistribution terms;
- established which host ABIs can actually be built and run;
- identified the smallest legally distributable test executable;
- defined build, install, launch, and failure-log acceptance criteria.


## Compatibilidade com o dispositivo de desenvolvimento

O dispositivo de desenvolvimento reportado pelo usuário é o Infinix Hot 50i (X6531B), com MediaTek Helio G81 e GPU Mali-G52 MC2. A página de especificações da Infinix confirma o Helio G81; uma ficha técnica independente identifica a GPU Mali-G52 MC2 (referências: https://www.meuinfinix.com.br/smartphone-infinix-hot-50i e https://support.halabtech.com/specs/infinix-hot-50i).

A variante Winlator WB64 consultada declara como requisitos mínimos Snapdragon 460 ou superior e GPU Adreno 610 ou superior, além de Vulkan 1.1. Portanto, **não devemos presumir que essa distribuição funcione no Hot 50i**; o requisito de GPU publicado não corresponde ao Mali deste aparelho. Isso não prova que todo runtime Winlator seja impossível, mas torna obrigatório verificar o backend gráfico real e testar no aparelho antes de escolher essa variante (fonte: https://github.com/winebox64/winlator).

### Decisão prática
- Manter o build Android leve e independente do runtime.
- Usar o PocketWin para recolher diagnóstico real (ABI, RAM, Android e modelo) antes de decidir qual backend é viável.
- Investigar separadamente suporte Vulkan/Mali e variantes de runtime com backend compatível; não prometer desempenho nem execução no Hot 50i antes de um teste.
- Se o primeiro runtime não suportar o hardware do usuário, documentar a incompatibilidade em vez de forçar uma integração que só funciona em Snapdragon/Adreno.
