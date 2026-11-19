class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list= new ArrayList<>();
        ListNode current=head;

        if (head == null || head.next == null) {
            return true;
        }
        while (current!=null){
            list.add(current.val);
            current=current.next;
        }
        int left=0;
        int right=list.size()-1;
        while (left<right){
            if (!list.get(left).equals(list.get(right))){
                return false;

            }
            left++;
            right--;
        
        }
        return true;
    }
}
