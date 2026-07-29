import TaskApp from './components/TaskApp';

function App() {
    return (
        <div style={{ maxWidth: '600px', margin: '40px auto', padding: '0 16px' }}>
            <h1>タスク管理アプリ</h1>
            <TaskApp />
        </div>
    );
}

export default App;