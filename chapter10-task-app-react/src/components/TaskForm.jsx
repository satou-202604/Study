import { useState } from 'react';


function TaskForm({onAddTask}){


    const [inputValue,setInputValue]=useState('');

    const [priority,setPriority]=useState('medium');




    const handleSubmit=(e)=>{


        e.preventDefault();


        if(inputValue.trim()===''){

            return;

        }



        onAddTask(

            inputValue.trim(),

            priority

        );



        setInputValue('');

    };




    return (

        <form onSubmit={handleSubmit}>


            <input

                type="text"

                value={inputValue}

                onChange={(e)=>

                    setInputValue(e.target.value)

                }

                placeholder="新しいタスクを入力"

            />





            <select

                value={priority}

                onChange={(e)=>

                    setPriority(e.target.value)

                }

            >

                <option value="high">
                    高
                </option>


                <option value="medium">
                    中
                </option>


                <option value="low">
                    低
                </option>


            </select>





            <button type="submit">

                追加

            </button>


        </form>

    );

}


export default TaskForm;