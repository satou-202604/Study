import { useState } from 'react';


function TaskItem({
    task,
    onToggle,
    onDelete,
    onEdit
}){


    const [isEditing,setIsEditing]=useState(false);


    const [editTitle,setEditTitle]=useState(task.title);




    const getPriorityLabel=(priority)=>{


        if(priority==='high'){

            return '高';

        }


        if(priority==='medium'){

            return '中';

        }


        return '低';

    };




    return (

        <li>


            <input

                type="checkbox"

                checked={task.status==='done'}

                onChange={()=>onToggle(task.id)}

            />





            {

            isEditing

            ?

            <input

                value={editTitle}

                onChange={(e)=>

                    setEditTitle(e.target.value)

                }

            />


            :

            <span>

                {task.title}

            </span>

            }






            <span>

                {getPriorityLabel(task.priority)}

            </span>






            <span>

                {

                task.status==='done'

                ?

                '完了'

                :

                '未着手'

                }

            </span>





            {

            isEditing

            ?

            <button

                onClick={()=>{


                    onEdit(
                        task.id,
                        editTitle
                    );


                    setIsEditing(false);


                }}

            >

                保存

            </button>


            :

            <button

                onClick={()=>setIsEditing(true)}

            >

                編集

            </button>

            }





            <button

                onClick={()=>onDelete(task.id)}

            >

                削除

            </button>


        </li>

    );

}


export default TaskItem;