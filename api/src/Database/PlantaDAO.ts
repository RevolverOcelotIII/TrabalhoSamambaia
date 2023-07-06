import { Planta } from "../model/Planta";
import { executeSQLSelect, executeSQLInsert } from "./connection";
//interface para pegar os dados do formulário e inserir no banco


export default class PlantaDAO {
  //Cria uma planta nova no banco de dados, em seguida cria uma instância na tabela de relacionamento de certificações, e de portifólio
  //Retorna true se o insert foi realizado com sucesso, ou false, caso já exista uma planta com o mesmo CNPJ cadastrado no banco
  static createPlanta = async (planta: Planta) => {
    console.log(planta);
      const insertId = await executeSQLInsert(
        "INSERT INTO planta (nome_comum, nome_cientifico, nome_personalizado, ciclo, regagem, proxima_adubagem, banho_sol, planta_base_id, imagem_url) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
        [
          planta.nome_comum ?? "",
          planta.nome_cientifico ?? "",
          planta.nome_personalizado ?? "",
          planta.ciclo ?? "",
          planta.regagem ?? "",
          planta.proxima_adubagem ?? "",
          planta.banho_sol ?? "",
          planta.planta_base_id ?? 0,
          planta.imagem_url ?? "",
        ]
      );
      return insertId;
  };
  // Buscar no banco de dados as informações para preencher a lista de checkboxes do formulário
  static getAllPlantas = async () => {
    const result: Planta[] = (await executeSQLSelect(
      "select * from planta"
    ) as Planta[]);
    return JSON.stringify(result);
  };
}
