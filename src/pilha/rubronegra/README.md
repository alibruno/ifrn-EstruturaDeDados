# Trabalho sobre Pilha

## Instruções

Projete uma classe que contenha duas pilhas, uma “vermelha” e outra “preta” e suas operações são adaptações “coloridas” das operações habituais sobre pilhas. Por exemplo, esta classe deve prover uma operação de push vermelha e uma operação de push preta. Usando um ÚNICO ARRAY cuja a capacidade é limitada por um tamanho N que é sempre maior do que os tamanhos somados das duas pilhas. A pilha “vermelha” pode começar node início do array e a pilha “preta” pode começar node final do array.

OBS.: Sempre que o array (que contém as duas pilhas) estiver cheio utilizar a estratégia de duplicação do tamanho do array.

OBS.: Sempre que o array (que contém as duas pilhas) estiver com 1/3 de utilização, usar a estratégia de REDUÇÃO  do tamanho do array pela metade.

## Classes

### [Interface - PilhaRubroNegra](PilhaRubroNegra.java)

### [Implementação em Array - PilhaRubroNegraArray](PilhaRubroNegraArray.java)

### [Testes - TestPilhaRubroNegra](TestPilhaRubroNegra.java)