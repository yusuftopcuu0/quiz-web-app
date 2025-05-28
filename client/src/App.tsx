import axios from 'axios'
import { useEffect, useState } from 'react'

interface User {
  id: number
  name: string
  email: string
}

function App() {
  const [users, setUsers] = useState<User[]>([])

  useEffect(() => {
    axios
      .get<User[]>('https://jsonplaceholder.typicode.com/users')
      .then((res) => setUsers(res.data))
      .catch((err) => console.error(err))
  }, [])

  return (
    <div className="p-4">
      <h1 className="text-2xl mb-4">Kullanıcılar</h1>
      <ul className="space-y-2">
        {users.map((user) => (
          <li key={user.id} className="p-2 border rounded">
            {user.name} - {user.email}
          </li>
        ))}
      </ul>
    </div>
  )
}

export default App
