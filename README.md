# 🛒 Sistema de Processamento de Pedidos — Refatoração com Padrões de Projeto

> Atividade prática da disciplina de **Métodos Avançados de Programação**  
> Refatoração de código legado utilizando padrões de projeto GoF

---

## 📋 Sobre o Projeto

Este projeto consiste na refatoração de um sistema legado de processamento de pedidos de uma loja virtual. O código original utilizava estruturas monolíticas com múltiplos `if/else` encadeados para lidar com descontos, fretes, impostos, pagamentos e notificações.

A refatoração aplicou os padrões de projeto **Strategy** e **Decorator** para tornar o sistema modular, extensível e de fácil manutenção.

---

## 🎯 Padrões de Projeto Aplicados

### 🔷 Strategy

O padrão Strategy foi utilizado para encapsular cada comportamento variável em sua própria classe, permitindo que algoritmos sejam trocados em tempo de execução sem alterar o código cliente.

| Interface | Implementações | Responsabilidade |
|---|---|---|
| `DescontoStrategy` | `DescontoCliente`, `DescontoCupom` | Cálculo de descontos |
| `FreteStrategy` | `FreteSedex`, `FreteLoggi`, `FreteGratis` | Cálculo de frete |
| `ImpostoStrategy` | `ImpostoPorEstado` | Cálculo de imposto por UF |
| `PagamentoStrategy` | `PagamentoCartao`, `PagamentoPix`, `PagamentoBoleto`, `PagamentoPayPal` | Processamento de pagamento |
| `NotificacaoStrategy` | `NotificacaoEmail`, `NotificacaoSMS`, `NotificacaoWhatsApp` | Envio de notificações |

### 🔷 Decorator

O padrão Decorator foi aplicado para adicionar comportamentos extras às estratégias de forma transparente, sem criar subclasses para cada combinação possível.

| Decorator | Comportamento adicionado |
|---|---|
| `LimiteDescontoDecorator` | Limita o desconto total a no máximo 40% do valor do pedido |
| `FreteGratisCupomDecorator` | Zera o frete quando o cupom `FRETEGRATIS` é aplicado (exceto para SEDEX) |
| `PayPalComTaxaDecorator` | Acrescenta a taxa de 3% nos pagamentos via PayPal |

---

## 🗂️ Estrutura do Projeto

```
loja/
├── src/
│   ├── Main.java                        # Ponto de entrada com casos de teste
│   ├── Pedido.java                      # Modelo de dados do pedido
│   ├── ProcessadorPedido.java           # Orquestrador principal
│   │
│   ├── # Enums
│   ├── Estado.java
│   ├── TipoCliente.java
│   ├── TipoCupom.java
│   ├── TipoFrete.java
│   ├── FormaPagamento.java
│   │
│   ├── # Strategy — Desconto
│   ├── DescontoStrategy.java
│   ├── DescontoCliente.java
│   ├── DescontoCupom.java
│   │
│   ├── # Decorator — Desconto
│   ├── LimiteDescontoDecorator.java
│   │
│   ├── # Strategy — Frete
│   ├── FreteStrategy.java
│   ├── FreteSedex.java
│   ├── FreteLoggi.java
│   ├── FreteGratis.java
│   │
│   ├── # Decorator — Frete
│   ├── FreteGratisCupomDecorator.java
│   │
│   ├── # Strategy — Imposto
│   ├── ImpostoStrategy.java
│   ├── ImpostoPorEstado.java
│   │
│   ├── # Strategy — Pagamento
│   ├── PagamentoStrategy.java
│   ├── PagamentoCartao.java
│   ├── PagamentoPix.java
│   ├── PagamentoBoleto.java
│   ├── PagamentoPayPal.java
│   │
│   ├── # Decorator — Pagamento
│   ├── PayPalComTaxaDecorator.java
│   │
│   ├── # Strategy — Notificação
│   ├── NotificacaoStrategy.java
│   ├── NotificacaoEmail.java
│   ├── NotificacaoSMS.java
│   └── NotificacaoWhatsApp.java
│
└── README.md
```

---

## ⚙️ Regras de Negócio

### Descontos por tipo de cliente
| Tipo | Desconto |
|---|---|
| COMUM | 0% |
| VIP | 10% |
| PREMIUM | 15% |
| FUNCIONARIO | 30% |

> O desconto total (cliente + cupom) é limitado a **40%** pelo `LimiteDescontoDecorator`.

### Cupons disponíveis
| Cupom | Efeito |
|---|---|
| `NATAL10` | 10% de desconto no valor dos produtos |
| `BLACKFRIDAY` | 25% de desconto no valor dos produtos |
| `FRETEGRATIS` | Zera o frete (apenas para LOGGI; não se aplica ao SEDEX) |

### Fretes
| Modalidade | Valor base | Embrulho presente |
|---|---|---|
| SEDEX | R$ 30,00 | + R$ 5,00 |
| LOGGI | R$ 15,00 | + R$ 5,00 |
| GRÁTIS | R$ 0,00 | — |

### Impostos
Calculados por alíquota de acordo com o estado do cliente. Exemplos:
- SP: 20% · RJ: 15% · MG: 10% · GO: 16%
- Demais estados: 8% (padrão)

### Pagamentos
| Forma | Observação |
|---|---|
| Cartão | Sem taxa adicional |
| PIX | Sem taxa adicional |
| Boleto | Sem taxa adicional |
| PayPal | + 3% de taxa (via Decorator) |

---

## ▶️ Como Executar

**Pré-requisito:** JDK 11 ou superior instalado.

```bash
# 1. Compile todos os arquivos dentro de src/
javac *.java 

# 2. Execute a classe principal
java .\Main.java 
```

---

## 🧪 Casos de Teste

O `Main.java` já inclui 4 cenários de teste:

| Pedido | Cliente | Tipo | Cupom | Frete | Pagamento |
|---|---|---|---|---|---|
| P1 | Ana | VIP | NATAL10 | SEDEX | PayPal (+3%) |
| P2 | Carlos | PREMIUM | — | LOGGI | PIX |
| P3 | Maria | COMUM | FRETEGRATIS | LOGGI | Boleto |
| P4 | João | VIP | FRETEGRATIS | SEDEX | Cartão |

> **P3** valida que o cupom `FRETEGRATIS` zera o frete no LOGGI.  
> **P4** valida que o cupom `FRETEGRATIS` **não** zera o frete no SEDEX.

---

## 💡 Benefícios da Refatoração

- **Aberto para extensão, fechado para modificação (OCP):** adicionar um novo tipo de pagamento ou frete não exige alterar o `ProcessadorPedido`.
- **Eliminação de condicionais aninhados:** cada decisão ficou isolada em sua própria classe.
- **Composição de comportamentos:** os Decorators permitem combinar regras (ex: desconto com limite, PayPal com taxa) sem explosão de subclasses.
- **Testabilidade:** cada estratégia pode ser testada isoladamente.
