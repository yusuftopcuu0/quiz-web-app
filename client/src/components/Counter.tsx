import { useCounterStore } from '../store/useCounterStore'

const Counter = () => {
  const { count, increase, decrease, reset } = useCounterStore()

  return (
    <div className="text-center mt-10">
      <h2 className="text-2xl">Count: {count}</h2>

      <div>
        <button onClick={increase} className="bg-green-500 text-white px-4 py-2 rounded">
          +1
        </button>
        <button onClick={decrease} className="bg-red-500 text-white px-4 py-2 rounded">
          -1
        </button>
        <button onClick={reset} className="bg-gray-500 text-white px-4 py-2 rounded">
          Reset
        </button>
      </div>
    </div>
  )
}

export default Counter
