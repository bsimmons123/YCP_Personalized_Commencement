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
			    
				// instantiate the id variables
	  			let idString;
			    
			    // instantiate list of scanned QR codes that will hold each qr that gets scanned from here on.
			    let scannedQRCodes = [];
			    
			    /**
			    * On a successful QR scan, update the slides in the HTML document based off of the student ID read from the scanned QR code.
			    */
			    function onScanSuccess(decodedText) {
					// if the value pulled from the qr is an id that was not already scanned, continue; otherwise, do nothing.
			        if (!scannedQRCodes.includes(decodedText)) {	
						// set id as string that was read
		    			id = decodedText; 
	
						// add the id scanned to a list of scanned id's
			        	scannedQRCodes.push(id);
			        	
			        	// Initialize old audio and slide outside of for loop to save time
			        	let oldAudio = audioDiv.innerHTML;
			        	let oldSlide = rightCarousel.innerHTML;
			        	
			        	// Instantiate new audio and slide variables to hold the innerhtml to replace the old versions of themselves
			    		let newAudio;
			    		let newSlide;
		    			
		    			// if the qr scanned does not contain the number 101 (This specific QR [101] will output the end slides of the ceremony when scanned.)
			        	if (IDs.includes(id) && id != "100" && id != "101") {
				    		try {
				    			for (let i = 0; i < IDs.length; i++) { // for loop to iterate through the array of student ID's
				    				if (IDs[i].localeCompare(id) == 0) { // if the studentID at i == the ID pulled from just scanned qr
				    					if (pictures[i] === '') { // if no picture for student
				    						if (audios[i] === '') { // if no audio for student
				    							newAudio = "<audio autoplay id='sound' src=''>";
				    							if (extraCurApprovals[i] === '0') { // if student has no picture, no audio, and extraCur is not approved:
				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
				    							} else { // if student has no picture, no audio, but extraCur is approved:
				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
				    							}
				    						} else { // if student has audio
				    							if (audioApprovals[i] === '0') { // if audio is not approved
				    								newAudio = "<audio autoplay id='sound' src=''>";
				    							} else { // if audio is approved
				    								newAudio = "<audio autoplay id='sound' src='" + ctx + "/files/" + fNames[i] + lNames[i] +  "/" + audios[i] + "'>";
				    							}
				    							if (extraCurApprovals[i] === '0') { // if student has no picture, has audio, and extraCur is not approved:
				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
				    							} else { // if student has no picture, has audio, but extraCur is approved:
				    								newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
				    							}	
				    						}
				    					} else { // if student has picture
				    						if (audios[i] === '') { // if no audio for student
				    							newAudio = "<audio autoplay id='sound' src=''>";
				    							if (imageApprovals[i] ==='0') { // if image is not approved 
				    								if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
						    						} else { // if extraCur is approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
						    						}
					    						} else { // if image is approved
					    							if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
						    						} else { // if extraCur is approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
						    						}
					    						}				    						
				    						} else { // if student has audio
				    							if (audioApprovals[i] === '0') { // if audio is not approved
				    								newAudio = "<audio autoplay id='sound' src=''>";
				    							} else { // if audio is approved
				    								newAudio = "<audio autoplay id='sound' src='" + ctx + "/files/" + fNames[i] + lNames[i] +  "/" + audios[i] + "'>";
				    							}
				    							if (imageApprovals[i] ==='0') { // if image is not approved 
				    								if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
						    						} else { // if extraCur is approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/browser-images/York.jpeg'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
						    						}
					    						} else { // if image is approved
					    							if (extraCurApprovals[i] ==='0') { // if extraCur is not approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: <br></p></div></div>";
						    						} else { // if extraCur is approved
				    									newSlide = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='width:100%; height: inherit' src='" + ctx + "/files/" + fNames[i] + lNames[i] + "/" + pictures[i] + "'><div class='carousel-caption d-none d-md-block' style='background-color: rgba(0, 90, 0, .7); width: 60%; height: 30%; margin-left: auto; margin-right: auto;'><h5 style='border-bottom: 2px solid white; width: 50%; margin-left: auto; margin-right: auto;'>" + fNames[i] + " " + lNames[i] + "</h5><p style='text-align: left; margin-left: 5%; font-size: 14px;'><strong>Majors</strong>: " + majors[i] + "<br><strong>Minors</strong>: " + minors[i] + "<br><strong>GPA</strong>: " + GPAs[i] + "<br><strong>Awards</strong>: " + awards[i] + "<br><strong>Extracurricular Activities</strong>: " + extras[i] + "<br></p></div></div>";
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
				    			console.log("QR could not be read.");
				    			return;
				    		}; 
			        	} else if (id === "100") { // if QR scanned reads "100" (Output the list of students who didn't scan)
							// take away the audio playing
							newAudio = "<audio autoplay id='sound' src=''>";
							
							let notThereIDs = []; // list of IDs of those that did not scan
							let notThereFirsts = []; // list of first names of those that did not scan
							let notThereLasts = []; // list of last names of those that did not scan
							let notThereMajors = []; // list of majors of those who did not scan
							
							// iterate through list of student ID's to see if they scanned or not
							for (i=0; i < IDs.length; i++) {
								if (!scannedQRCodes.includes(IDs[i])) { // if student ID is not in list of scanned QR codes
									notThereIDs.push(IDs[i]); // add students ID
									notThereFirsts.push(fNames[i]); // add students first name
									notThereLasts.push(lNames[i]); // add students last name
									notThereMajors.push(majors[i]); // add students major
								}
							}
			
							// variable to add new lines on slide for each student who didn't attend
							let listOfStudents = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><div style='margin: 0; position: absolute; top: 50%; left: 50%; -ms-transform: translate(-50%, -50%); transform: translate(-50%, -50%);'>";
							
							// add line to listOfStudents slide for each student that did not attend
							for (i = 0; i < notThereIDs.length; i++) {
								listOfStudents += "<p style='color: white; text-align: left; font-size: 18px; width: 100%; margin: auto;'><strong>" + notThereFirsts[i] + " " + notThereLasts[i] + " - " + notThereMajors[i] + "</strong></p>";
							}
							
							// add endtag to listOfStudents slide
							listOfStudents += "</div></div>";
							
							// set the left slide as an image that informs viewers of those that couldn't be there
							let left = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='height: inherit' src='" + ctx + "/browser-images/MissingStudents.png'></div>";
			        		
							// set the right slide as a line for each name of the students that couldn't be there (didn't scan)
							let right = listOfStudents;
							
							// replace the existing HTML with the newly set HTML variables
							audioDiv.innerHTML = audioDiv.innerHTML.replace(oldAudio, newAudio)
			        		leftCarousel.innerHTML = leftCarousel.innerHTML.replace(leftCarousel.innerHTML, left);
			        		rightCarousel.innerHTML = rightCarousel.innerHTML.replace(rightCarousel.innerHTML, right);
								
							// add QR to list of scanned QR's to avoid an accidental re-scan
							scannedQRCodes.push(id);

			        	} else if (id === "101") { // if QR scanned reads "101" (Output the slides for the end of the ceremony)
							// take away the audio playing
			        		newAudio = "<audio autoplay id='sound' src=''>";
							
							// set left and right slides of congrats image as variables
			        		let finalLeft = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='height: inherit' src='" + ctx + "/browser-images/finalSlideLeft.png'></div>";
			        		let finalRight = "<div class='carousel-item active' id='left-slide' style='height: inherit;'><img class='d-block w-100' style='height: inherit' src='" + ctx + "/browser-images/finalSlideRight.png'></div>";
			        		
							// replace the existing HTML with the newly set HTML variables
							audioDiv.innerHTML = audioDiv.innerHTML.replace(oldAudio, newAudio)
			        		leftCarousel.innerHTML = leftCarousel.innerHTML.replace(leftCarousel.innerHTML, finalLeft);
			        		rightCarousel.innerHTML = rightCarousel.innerHTML.replace(rightCarousel.innerHTML, finalRight);

							// add QR to list of scanned QR's to avoid an accidental re-scan
							scannedQRCodes.push(id);
						}
			        }
			    }
				// render the new HTML content of each slide on a successful scan
			    html5QrcodeScanner.render(onScanSuccess);
			});
		}
	}).catch( err => {
		// handle error if unable to get camera ID
		console.log("Couldn't get camera ID.  Error: " + err);
	});