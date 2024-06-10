/*
 * Copyright 2002-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ramenpedia.controller.member;

import com.ramenpedia.base.ApiResponse;
import com.ramenpedia.controller.member.dto.GoogleLoginReq;
import com.ramenpedia.service.OAuth2LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member/OAuth2")
public class OAuth2LoginController {

	@Autowired
	private OAuth2LoginService oAuth2LoginService;

	@PostMapping("/google")
	public ApiResponse<String> google(@RequestBody GoogleLoginReq req) throws Exception {
		req.valid();
		oAuth2LoginService.google(req.getToken());
		return ApiResponse.getSuccessInstance();
	}
}
