export function getData(): TaskData{
    return{
        input:{
            type:'matrix',
            data:[['0','1','0','?','1'],['1','1','?'],['?','?','?']]
        },
        answer:{
            type:'matrix',
            data:[['?','?','?','?','?'],['?','?','?','?','?','?']]
        }
    }
}
export interface TaskData{
    input:Data,
    answer:Data,
}
export interface Data {
    type:string
    data:any
}