/**
     * Function that checks if scanner is ready to go and adds the event listener 
     */
     function docReady(fn) {
 	     // see if DOM is already available
 	     if (document.readyState === "complete" || document.readyState === "interactive") {
 	         // call on next available tick
 	         setTimeout(fn, 1);
 	     } else {
 	         document.addEventListener("DOMContentLoaded", fn);
 	     }
 	 } 
     
  	/**
  	* Triggers user permissions (but skips the approval) and activates docReady function if there is a camera.
  	*/
 	Html5Qrcode.getCameras().then(devices => {
 		// devices would be an array of objects of type: { id: "id", label: "label" }
 		if (devices && devices.length) {
 			docReady(function() {
 				// create a const for the QR scanner (this will render each result later on)
 				const html5QrcodeScanner = new Html5QrcodeScanner("qr-reader", { fps: 10, qrbox: 200 });
 				
 				// initalize the carousels and audio div to be used throughout the onSuccess method.
 				let rightCarousel = document.getElementById("right-inner");
 			    let leftCarousel= document.getElementById("left-inner");
 			    let audioDiv = document.getElementById("audio");
 			 	
 			 	// initialize the lastResult variable and the count variable
 			    let lastResult = 0;
 			    let count = 0;
 			    
 				// instantiate the id variables
 	  			let idString;
 	        	let scannedNow;
 	        	let scannedBefore;
 			  
 	        	// create constant js list for every property containing each students value of that property
 			    //const IDs = <%=getJSArray(stuIDs)%>; 
 			    //const fNames = <%=getJSArray(stuFirstNames)%>;
 			    //const lNames = <%=getJSArray(stuLastNames)%>; 
 			    //const majors = <%=getJSArray(stuMajors)%>; 
 			    //const minors = <%=getJSArray(stuMinors)%>;
 			    //const GPAs = <%=getJSArray(stuGPAs)%>; 
 			    //const extras = <%=getJSArray(stuExtras)%>; 
 			    //const awards = <%=getJSArray(stuAwards)%>; 
 			    //const pictures = <%=getJSArray(stuPictures)%>; 
 			    //const audios = <%=getJSArray(stuAudios)%>;     
 			    //const extraCurApprovals = <%=getJSArray(extraCurApprovals)%>;
 			    //const imageApprovals = <%=getJSArray(imageApprovals)%>;
 			    //const audioApprovals = <%=getJSArray(audioApprovals)%>;
 			    
 			    // instantiate list of scanned QR codes that will hold each qr that gets scanned from here on.
 			    let scannedQRCodes = [];
 			    
 			    /**
 			    * On a successful QR scan, update the slides in the HTML document based off of the student ID read from the scanned QR code.
 			    */
 			    function onScanSuccess(decodedText, decodedResult) {
 					// if the value pulled from the qr is an id that was not already scanned, continue; otherwise, do nothing.
 			        if (!scannedQRCodes.includes(decodedText)) {	
 			        	// prevents the same student from scanning again by adding the id scanned to a list of scanned id's
 			        	scannedQRCodes.push(decodedText); 
 			        	
 			        	// Initialize old audio and slide outside of for loop to save time
 			        	let oldAudio = audioDiv.innerHTML;
 			        	let oldSlide = rightCarousel.innerHTML;
 			        	
 			        	// Instantiate new audio and slide variables to hold the innerhtml to replace the old versions of themselves
 			    		let newAudio;
 			    		let newSlide;
 			    		
 			    		// set id as string that was read
 		    			idString = decodedText; 
 		    			
 		    			// set id scanned
 		    			scannedNow = parseInt(idString);
 		    			
 		    			// if the qr scanned does not contain the number 101 (This specific QR [101] will output the end slides of the ceremony when scanned.)
 			        	if (idString != "101") {
 				    		try {
 				    			for (let i = 0; i < IDs.length; i++) { // for loop to iterate through the array of student ID's
 				    				if (IDs[i].localeCompare(idString) == 0) { // if the studentID at i == the ID pulled from just scanned qr
 				    					
 				    					
 				    					if (pictures[i] === '') { // if no picture for student
 				    						
 				    						if (audios[i] === '') { // if no audio for student
 				    							newAudio = "<audio autoplay id='sound' src=''>";
 				    							
 				    							if (extraCurApprovals[i] === '0') { // if student has no picture, no audio, and extraCur is not approved:
 				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 				    							
 				    							} else { // if student has no picture, no audio, but extraCur is approved:
 				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 				    							}
 				    							
 				    						} else { // if student has audio
 				    							
 				    							if (audioApprovals[i] === '0') { // if audio is not approved
 				    								newAudio = "<audio autoplay id='sound' src=''>";
 				    							} else { // if audio is approved
 				    								newAudio = "<audio autoplay id='sound' src='${pageContext.servletContext.contextPath}/files/" + fNames[i] + lNames[i] +  "/" + audios[i] + "'>";
 				    							}
 				    		
 				    							if (extraCurApprovals[i] === '0') { // if student has no picture, has audio, and extraCur is not approved:
 				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 				    							} else { // if student has no picture, has audio, but extraCur is approved:
 				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 				    							}	
 				    						}
 				    					
 				    					} else { // if student has picture
 				    						
 				    						if (audios[i] === '') { // if no audio for student
 				    							newAudio = "<audio autoplay id='sound' src=''>";
 				    							if (imageApprovals[i] ==='0') { // if image is not approved 
 				    								if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 						    						} else { // if extraCur is approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 						    						}
 					    						} else { // if image is approved
 					    							if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 						    						} else { // if extraCur is approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 						    						}
 					    						}				    						
 				    						} else { // if student has audio
 				    							if (audioApprovals[i] === '0') { // if audio is not approved
 				    								newAudio = "<audio autoplay id='sound' src=''>";
 				    							} else { // if audio is approved
 				    								newAudio = "<audio autoplay id='sound' src='${pageContext.servletContext.contextPath}/files/" + fNames[i] + lNames[i] +  "/" + audios[i] + "'>";
 				    							}
 				    							if (imageApprovals[i] ==='0') { // if image is not approved 
 				    								if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 						    						} else { // if extraCur is approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 						    						}
 					    						} else { // if image is approved
 					    							if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
 						    						} else { // if extraCur is approved
 				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='${pageContext.request.contextPath}/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
 						    						}
 					    						}
 				    						}
 				    					
 				    					}
 				    					// set the innerHTML of the audio and slides to replace what was just on the screen
 				    					audioDiv.innerHTML = audioDiv.innerHTML.replace(oldAudio, newAudio)
 		    							leftCarousel.innerHTML = leftCarousel.innerHTML.replace(leftCarousel.innerHTML, oldSlide);
 				    					rightCarousel.innerHTML = rightCarousel.innerHTML.replace(oldSlide, newSlide);
 				    				}
 				    			}
 				    		} catch {
 				    			// cancel the scan if qr data can't be read (i.e. A smudged QR -- Not applicable in our case except for testing purposes)
 				    			console.log("Can't read the QR data.");
 				    			return;
 				    		};
 				    		// increase the counter, set last result to idString, and set old id to scanned id
 				    		++count; 
 				            lastResult = idString; 
 				            scannedBefore = scannedNow; 
 			        	} else { // if QR scanned reads "101"
 			        		// set the innerHTML of the final slides after all students have scanned
 			        		newAudio = "<audio autoplay id='sound' src=''>";
 			        		let finalLeft = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='height: inherit' src='${pageContext.request.contextPath}/browser-images/finalSlideLeft.png'></div>";
 			        		let finalRight = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='height: inherit' src='${pageContext.request.contextPath}/browser-images/finalSlideRight.png'></div>";
 			        		audioDiv.innerHTML = audioDiv.innerHTML.replace(oldAudio, newAudio)
 			        		leftCarousel.innerHTML = leftCarousel.innerHTML.replace(leftCarousel.innerHTML, finalLeft);
 			        		rightCarousel.innerHTML = rightCarousel.innerHTML.replace(rightCarousel.innerHTML, finalRight);
 			        	}
 			        }
 			    }
 				// render the new innerHTML content of each slide on a successful scan
 			    html5QrcodeScanner.render(onScanSuccess);
 			});
 		}
 	}).catch(err => {
 		// handle error if unable to get camera ID
 		console.log("Couldn't get the camera ID");
 	});