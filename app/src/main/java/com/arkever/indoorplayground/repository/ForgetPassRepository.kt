import com.arkever.indoorplayground.model.DTOs.ForgetPassDto
import com.arkever.indoorplayground.model.Responses.ForgetPassResponse
import com.arkever.indoorplayground.networks.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgetPassRepository {

    fun forgetPassword(email: String , onResult: (Boolean,String)->Unit)
    {
        val forgetPassDto = ForgetPassDto(email)
        RetrofitClient.instance.forgetPassword(forgetPassDto).enqueue(object :
            Callback<ForgetPassResponse> {
            override fun onResponse(
                call: Call<ForgetPassResponse>,
                response: Response<ForgetPassResponse>
            ) {
                val body = response.body()
                if (body != null) {
                    if (body.statusCode == 200) {
                        onResult(true, body.message)
                    } else {

                        onResult(false, body.message)
                    }
                } else {
                    onResult(false, "Empty response from server")
                }
            }

            override fun onFailure(call: Call<ForgetPassResponse>, t: Throwable) {
                onResult(false, t.localizedMessage ?: "Unknown error")
            }
        })
    }
}
