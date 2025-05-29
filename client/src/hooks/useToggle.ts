import { type Dispatch, type SetStateAction, useState } from 'react';

export default function useToggle(
  initialValue?: boolean
): [boolean, () => void, Dispatch<SetStateAction<boolean>>] {
  const [value, setValue] = useState(!!initialValue);

  return [value, () => setValue(prev => !prev), setValue];
}
