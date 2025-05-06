# language: pt
Funcionalidade: Carregamento e Desempenho
  Como um usuário da Amazon
  Eu quero que a página inicial carregue rapidamente
  Para ter uma experiência de navegação fluida

  Contexto:
    Dado o usuário está na página inicial da Amazon

  @positivo @performance @performancePositivo @regressivos
  Cenário: Carregar a página inicial em tempo aceitável
    Então a página deve carregar dentro do tempo aceitável
    E as métricas de desempenho devem ser coletadas

  @positivo @performance @performancePositivo @regressivos
  Cenário: Carregar a página em diferentes navegadores
    Então a página deve carregar em menos de 5.0 segundos
    E as métricas de desempenho devem ser coletadas

  @positivo @performance @performancePositivo @regressivos
  Esquema do Cenário: Verificar desempenho em diferentes tamanhos de tela
    Quando o usuário redimensiona a janela para <largura>x<altura>
    Então a página deve carregar em menos de 5.0 segundos
    E as métricas de desempenho devem ser coletadas

    Exemplos:
      | largura | altura |
      | 1920    | 1080   |
      | 1366    | 768    |
      | 768     | 1024   |
      | 375     | 667    |

  @negativo @performance @performanceNegativo @regressivos
  Cenário: Verificar desempenho com conexão lenta
    # Nota: Este cenário requer simulação de rede lenta
    # que será implementada no código de teste
    Então a página deve carregar dentro do tempo aceitável
    E as métricas de desempenho devem ser coletadas
