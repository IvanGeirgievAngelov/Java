import java.util.Arrays;

public class asd {

		public static int swap(int  a, int b){
	
			return a;
		}
		
		public static int[] sort(int[] nums){
		
			for(int i=0;i<nums.length-1;i++){
				for(int j=i+1;j<nums.length;j++){
					if(nums[i]>nums[j]){
						//kofti swap funkciq;	
						nums[j]=swap(nums[i],nums[i]=nums[j]);
					}
				}
				
			}
			return nums;
		}
		
		public static void main(String[] args){
			int[] arr={5,4,-3,2,1};
			
			sort(arr);
			System.out.println(Arrays.toString(arr));
		}

	}