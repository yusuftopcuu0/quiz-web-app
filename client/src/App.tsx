import './App.css'
import { Button } from 'flowbite-react'
import { useNavigate, Routes, Route } from 'react-router-dom'
import Example from './example'

function App() {
  const navigate = useNavigate()

  const handleClick = () => {
    navigate('/example')
  }
  return (
    <>
      <Routes>
        <Route path="/example" element={<Example />} />
      </Routes>
      <div>
        <h1 className="text-2xl">Hello, World!</h1>
        <Button onClick={handleClick}>Go to Example</Button>
      </div>
    </>
  )
}

export default App
