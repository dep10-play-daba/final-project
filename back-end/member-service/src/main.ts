import express from 'express';
import env from 'dotenv'
import {router as MemberRouter} from "./api/member-rest-controller";

env.config();
const app=express();
app.use("/api/v1/members",MemberRouter)
app.listen(process.env.APP_PORT,()=>console.log(`server has  been started at port ${process.env.APP_PORT}`))