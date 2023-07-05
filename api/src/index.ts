//Necessário para a utilização de imports no arquivo
export {};

import express, { Request, Response } from "express";
import bodyParser from "body-parser";
import cors from "cors";
import helmet from "helmet";
import morgan from "morgan";
import { Planta } from "./model/Planta";
import PlantaDAO from "./Database/PlantaDAO";

const app = express();

app.use(helmet());

app.use(bodyParser.json());

app.use(cors());

app.use(morgan("combined"));

//Adiciona uma planta nova no banco de dados ao receber a requisição
app.post("/adicionarPlanta", (req: Request, res: Response) => {
  console.log(req.body);
  
  PlantaDAO.createPlanta((req.body ?? {}) as Planta).then((result) => {
    if (result > 0 ) {
      console.log(result);
      res.json({ id: result});
    } else {
      console.log("deu errado");
      res.json({ id: -1 });
    }
  });
  console.log("Inserção bem-sucedida");
});

app.get("/getPlantas", (req: Request, res: Response) => {
  PlantaDAO.getAllPlantas().then((result) => res.status(200).send(result));
});

app.listen(3001, () => {
  console.log("listeing to 3001");
});