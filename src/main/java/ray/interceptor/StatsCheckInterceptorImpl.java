package ray.interceptor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ray.data.param.StatsParamVo;
import ray.repository.StatsDao;
import ray.service.StatsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Calendar;
import java.util.Random;
import java.util.Set;

/**
 * 일일방문자를 체크해서 반환하는 인터셉터
 * Created by ChanPong on 2016-05-17.
 */
@Slf4j
@Service
@Component
public class StatsCheckInterceptorImpl extends HandlerInterceptorAdapter {
	@Autowired
	private StatsService statsService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		/* 브라우저 캐시를 제거한다 */
		response.setHeader("Progma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store"); // 일부 파이어폭스 버그 관련
		response.setContentType("text/html; charset=utf-8");

		String splitUri = "";
		String requestURI = request.getRequestURI();
		int uriSize = requestURI.split("/").length;
		if(uriSize > 1) {
			splitUri = requestURI.split("/")[1];
		}
		log.info("### requestURI : " + requestURI);

		/** 히어로배너 자동변경 */
		//이미지 선택번호
		int idx = 0;

		//각 계절별 폴더 이름
		String season = "";

		//각 계절별 이미지 보유 갯수
		Calendar cal = Calendar.getInstance();
		int month = (cal.get(Calendar.MONTH) + 1);

		Random r = new Random();
		if(month >= 3 && month <=5) { //봄(3~5월)
			season = "spring";
			idx = r.nextInt(getMainFileCount(request, "spring")) + 1;
		} else if(month >= 6 && month <=8) { //여름(6~8월)
			season = "summer";
			idx = r.nextInt(getMainFileCount(request, "summer")) + 1;
		} else if(month >= 9 && month <=11) { //가을(9~11월)
			season = "autumn";
			idx = r.nextInt(getMainFileCount(request, "autumn")) + 1;
		} else if(month >= 12 && month <=2) { //겨울(12~2월)
			season = "winter";
			idx = r.nextInt(getMainFileCount(request, "winter")) + 1;
		}

		request.setAttribute("season", season);
		request.setAttribute("imgIdx", idx);

		/** 일일방문자 체크 */
//		if("/".equals(requestURI) || (uriSize > 1 && "index".equals(splitUri) || (uriSize > 1 && "view".equals(splitUri)))) {
//			try {
//				StatsParamVo paramVo = new StatsParamVo();
//				paramVo.setTypeCode("today");
//				paramVo.setIp(request.getRemoteAddr());
//
//				if(!statsService.getStatsCnt(paramVo)) {
//					statsService.insertBufferVo(paramVo);
//					statsService.updateStatsVo(paramVo);
//				}
//			} catch(Exception e) {
//				e.printStackTrace();
//			}
//		}
		return true;
	}

	/** 각 계절별 폴더에 있는 파일들의 총 갯수 반환 */
	private int getMainFileCount(HttpServletRequest request, String typeCode) {
		File path = new File(request.getServletContext().getRealPath("/") + "image" + File.separator + "main" + File.separator + typeCode);
		return path.listFiles().length;
	}
}