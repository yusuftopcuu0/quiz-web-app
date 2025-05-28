import { toast } from 'react-toastify'
import { ToastContainer } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'

const MyComponent = () => {
  const handleClick = () => {
    toast.success('Başarıyla kaydedildi!')
    // toast.error('Bir hata oluştu!')
    // toast.info('Bilgilendirme mesajı')
    // toast.warn('Dikkat edilmesi gereken bir durum')
  }

  return (
    <>
      <button onClick={handleClick} className="bg-blue-500 text-white px-4 py-2 rounded">
        Bildirim Göster
      </button>
      <ToastContainer
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="dark"
      />
    </>
  )
}

export default MyComponent
