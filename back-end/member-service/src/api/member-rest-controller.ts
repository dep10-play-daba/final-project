import express, {json} from "express";
import cors from 'cors';
import {Collection,Document, MongoClient} from "mongodb";
import env from "dotenv";
import {Member} from "../dto/member";

env.config()
const mongo=new MongoClient(process.env.APP_DB_URL!);
let memberRepo:Collection<Document>;
async function main() {
    // Use connect method to connect to the server
    await mongo.connect();
    console.log('Connected successfully to mongodb server');
    const db = mongo.db(process.env.APP_DB_NAME);
    memberRepo=db.collection("member")
}

main();

export const router=express.Router();

router.use(json());
router.use(cors());

router.post("/",async (req, res)=>{
    try{

        const member:Member=req.body as Member;
        const validationErrors:Array<{field:string,error:string}>=[]
        if (!member._id?.trim())validationErrors.push({field:'_id',error:"Member ID can't be empty"});
        if (/^\d{9}[Vv]$/.test(member._id))validationErrors.push({field:"data-validation",error:"Member ID should be valid NIC"})

        if (!member.name?.trim())validationErrors.push( {field:'name',error:"Member Name can't be empty"});
        if (/^\[A-Za-z ]+$/.test(member.name))validationErrors.push( {field:"name",error:"Invalid Member Name"});

        if (!member.address?.trim())validationErrors.push( {field:'address',error:"Member Address can't be empty"})

        if (!member.contact?.trim())validationErrors.push( {field:'contact',error:"Member Contact number can't be empty"})
        if (/^\d{3}-\d{7}$/.test(member.name))validationErrors.push( {field:"contact",error:"Invalid Contact Number"})

        res.sendStatus(201);

    }catch (e:any){
        if (e.name && e.name==='data-valdation'){
            res.status(400).send(e.message)
        }
    }
        // await memberRepo.insertOne(req.body);
})

router.patch("/:memberId",(req, res)=>{
    res.send("<h1>Update Member</h1>");
})

router.delete("/:memberId",(req, res)=>{
    res.send("<h1>Delete Member</h1>");
})

router.get("/:memberId",(req, res)=>{
    res.send("<h1>Get Member</h1>");
})


router.get("/",async (req, res)=>{
    res.send(await memberRepo.find({}).toArray());
})