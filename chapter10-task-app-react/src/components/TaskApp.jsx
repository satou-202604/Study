import { useState, useEffect } from 'react';
import TaskForm from './TaskForm';
import TaskList from './TaskList';


function TaskApp() {

    // タスク一覧
    const [tasks, setTasks] = useState(() => {

        const savedTasks = localStorage.getItem('tasks');

        if (savedTasks) {
            return JSON.parse(savedTasks);
        }


        return [
            {
                id: 1,
                title: 'Reactのインストール',
                status: 'done',
                priority: 'high'
            },
            {
                id: 2,
                title: 'JSXの基礎を学ぶ',
                status: 'done',
                priority: 'high'
            },
            {
                id: 3,
                title: 'useStateを理解する',
                status: 'active',
                priority: 'high'
            },
            {
                id: 4,
                title: 'useEffectを学ぶ',
                status: 'active',
                priority: 'medium'
            },
            {
                id: 5,
                title: '演習問題を解く',
                status: 'pending',
                priority: 'low'
            }
        ];

    });


    // localStorage保存
    useEffect(() => {

        localStorage.setItem(
            'tasks',
            JSON.stringify(tasks)
        );

    }, [tasks]);



    // フィルター状態
    const [filterStatus, setFilterStatus] = useState('all');



    // タスク追加
    const addTask = (title, priority) => {

        const newTask = {

            id: Date.now(),

            title,

            status: 'pending',

            priority

        };


        setTasks(prev => [
            ...prev,
            newTask
        ]);

    };



    // 完了・未完了切替
    const toggleTask = (id) => {

        setTasks(prev =>

            prev.map(task =>

                task.id === id

                ?

                {
                    ...task,

                    status:
                        task.status === 'done'
                        ? 'pending'
                        : 'done'
                }

                :

                task

            )

        );

    };



    // 削除
    const deleteTask = (id) => {

        setTasks(prev =>

            prev.filter(task =>

                task.id !== id

            )

        );

    };



    // 編集
    const editTask = (id, newTitle) => {

        setTasks(prev =>

            prev.map(task =>

                task.id === id

                ?

                {
                    ...task,
                    title: newTitle
                }

                :

                task

            )

        );

    };



    // フィルタリング
    const filteredTasks = tasks.filter(task => {

        if (filterStatus === 'all') {

            return true;

        }

        return task.status === filterStatus;

    });



    return (

        <div>


            {/* タスク追加 */}
            <TaskForm
                onAddTask={addTask}
            />



            {/* フィルター */}
            <div style={{ margin: '16px 0' }}>

                <button
                    onClick={() => setFilterStatus('all')}
                >
                    すべて
                </button>


                <button
                    onClick={() => setFilterStatus('pending')}
                >
                    未着手
                </button>


                <button
                    onClick={() => setFilterStatus('active')}
                >
                    進行中
                </button>


                <button
                    onClick={() => setFilterStatus('done')}
                >
                    完了
                </button>

            </div>



            {/* タスク一覧 */}
            <TaskList

                tasks={filteredTasks}

                onToggle={toggleTask}

                onDelete={deleteTask}

                onEdit={editTask}

            />



            {/* サマリー */}
            <p style={{
                marginTop: '16px',
                color: '#666'
            }}>

                表示中:
                {filteredTasks.length}件 /

                全
                {tasks.length}件

                （完了:
                {
                    tasks.filter(
                        task => task.status === 'done'
                    ).length
                }
                件）

            </p>


        </div>

    );

}


export default TaskApp;