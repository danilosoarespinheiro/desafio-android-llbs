# Desafio Android

Este é um projeto simples e legado, onde utilizamos a api do trello para listar os boards do usuário.

Para conseguir as propriedades necessárias para essa requisição (`key`e `token`), utilize este [guia](https://developer.atlassian.com/cloud/trello/guides/rest-api/api-introduction/).

## Desafio se baseia em:

- Atualizar ou alterar dependências do projeto (Ex: adicionar Compose, remover/atualizar versões legadas das libs, etc...)
- Organizar o código do projeto utilizando algum padrão de sua preferência (MVVM, MVP, etc...).
- Utilize arquitetura limpa (ou inspirada em Clean Architecture)
- Validar possíveis falhas de segurança.
- Implementar injeção de dependencias
- Implementar programação reativa (rxJava ou coroutines).
- Implementar testes unitários.
- Implementar testes de UI. (Opcional)
- Configurar um CI/CD básico. (Opcional)

## Entregáveis
Link para um repositório Git (GitHub, GitLab, etc.) com o projeto completo.

Um bom README explicando como rodar o projeto, as decisões arquiteturais, e os pontos fortes da solução.

Boa sorte! =)

![Captura de Tela 2023-01-31 às 09 42 15](https://user-images.githubusercontent.com/8430108/215763921-67430db9-6ccc-4200-b87e-534bf81787ba.png)


# Como Rodar o Projeto

Para executar o projeto, siga os passos abaixo:

  1. Clone o Repositório:
  <img width="530" height="29" alt="Screenshot 2025-11-03 at 01 46 26" src="https://github.com/user-attachments/assets/3ea7292e-0cb2-459d-9cba-12c63de064f4" />

  2. Obtenha as Credenciais da API do Trello: O projeto requer uma chave (key) e um token de acesso (token) da API do Trello. Siga o guia oficial da Atlassian para gerá-los.

  3. Configure as Credenciais (Secrets): Este projeto utiliza o Secrets Gradle Plugin para gerenciar as credenciais de forma segura, evitando que sejam expostas no controle de versão.
   - No diretório raiz do projeto, crie ou edite o arquivo local.properties.
   - Adicione sua chave e token ao arquivo da seguinte forma:

  <img width="377" height="75" alt="Screenshot 2025-11-03 at 01 44 17" src="https://github.com/user-attachments/assets/d63919ff-02cb-4537-901f-41ec1f45aa35" />

  4. Abra e Execute no Android Studio:◦Abra o projeto no Android Studio.◦Aguarde o Gradle sincronizar as dependências.◦Execute o aplicativo em um emulador ou dispositivo físico. O app irá buscar e exibir os quadros associados às suas credenciais.


# Decisões Arquiteturais

A principal diretriz do desafio foi reestruturar o código aplicando padrões modernos. A arquitetura escolhida foi uma combinação de MVVM (Model-View-ViewModel) com os princípios da Clean Architecture, resultando em um código desacoplado, testável e escalável.
O projeto foi organizado em três camadas principais:

  1. Camada de Apresentação (Presentation)
     - Padrão: MVVM.
     - Responsabilidade: Exibir os dados na tela e capturar interações do usuário. É uma camada "burra" que apenas reage a estados.
     - Componentes:
       - View (BoardsFragment): Observa um StateFlow exposto pelo ViewModel e atualiza a UI (exibindo a lista, o loader ou mensagens de erro).
       - ViewModel (BoardsViewModel): Orquestra a busca de dados através dos Use Cases, gerencia o estado da UI (BoardUiState) e sobrevive a mudanças de configuração.
       - UI State (BoardUiState): Uma sealed class que modela todos os possíveis estados da tela: Loading, Success e Error.
  2. Camada de Domínio (Domain)
     - Responsabilidade: Conter a lógica de negócio pura da aplicação, livre de dependências do framework Android.
     - Componentes:
       - Model (Board.kt): Representa o objeto de negócio principal da forma que a UI precisa, sem anotações ou detalhes de implementação da camada de dados.
      - Use Case (GetBoardsUseCase.kt): Encapsula uma única regra de negócio (neste caso, "obter a lista de quadros"). Atua como uma ponte entre o ViewModel e o Repository.
  3. Camada de Dados (Data)
     - Responsabilidade: Buscar os dados de fontes externas (neste caso, a API do Trello) e fornecer para a camada de domínio.
     - Componentes:
       - Repository (BoardsRepository e BoardsRepositoryImpl): Implementa o padrão Repository, atuando como a única fonte de verdade para os dados dos quadros. Ele abstrai a origem dos dados (rede, banco de dados, cache) do resto do app.
       - Remote Data Source (TrelloApiService): Interface do Retrofit que define os endpoints da API.
       - DTO (BoardDto.kt): Data Transfer Object que mapeia exatamente a resposta JSON da API. Isso evita que detalhes da API "vazem" para as camadas de domínio e apresentação.


# Pontos Fortes da Solução

- Segurança em Primeiro Lugar:
  - Gerenciamento de Segredos: As credenciais da API são gerenciadas de forma segura com o Secrets Gradle Plugin, mantendo-as fora do controle de versão e do código-fonte compilado em texto claro.
- Arquitetura Robusta e Escalável:
  - A separação em camadas (Presentation, Domain, Data) torna o projeto fácil de manter e escalar. Adicionar uma nova fonte de dados (como um cache em banco de dados) ou uma nova feature se torna uma tarefa organizada.
  - O uso de injeção de dependências com Hilt desacopla os componentes, simplifica o código e é fundamental para a testabilidade.
- Programação Reativa e Moderna:
  - A comunicação entre as camadas e a atualização da UI são feitas de forma reativa utilizando Kotlin Coroutines e StateFlow. Isso resulta em um código assíncrono mais limpo, seguro e com melhor gerenciamento do ciclo de vida.
  - O projeto foi migrado de callbacks para suspend functions, eliminando o "callback hell" e melhorando a legibilidade.
- Testabilidade em Todos os Níveis:
    - Testes Unitários: Foram implementados para as camadas de ViewModel e Domain, validando a lógica de negócio de forma rápida e isolada com o uso de mock para as dependências.
- Qualidade e Manutenção do Código:
  - As dependências do projeto foram atualizadas para as últimas versões estáveis, eliminando bibliotecas legadas e vulnerabilidades conhecidas.
  - O código foi escrito inteiramente em Kotlin, seguindo as convenções da linguagem e aproveitando seus recursos modernos para criar um código mais conciso e seguro.
